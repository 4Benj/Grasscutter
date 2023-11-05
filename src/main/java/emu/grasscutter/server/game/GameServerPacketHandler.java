package emu.grasscutter.server.game;

import static emu.grasscutter.config.Configuration.GAME_INFO;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.Grasscutter.ServerDebugMode;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.server.event.game.ReceivePacketEvent;
import emu.grasscutter.server.game.GameSession.SessionState;
import emu.grasscutter.server.game.version.GameVersion;
import it.unimi.dsi.fastutil.ints.*;
import java.util.HashMap;

public final class GameServerPacketHandler {
    private final HashMap<PacketOpcodes, PacketHandler> handlers;

    public GameServerPacketHandler(Class<? extends PacketHandler> handlerClass) {
        this.handlers = new HashMap<>();

        this.registerHandlers(handlerClass);
    }

    public void registerPacketHandler(Class<? extends PacketHandler> handlerClass) {
        try {
            var opcode = handlerClass.getAnnotation(Opcodes.class);
            if (opcode == null || opcode.disabled()) {
                return;
            }

            var packetHandler = handlerClass.getDeclaredConstructor().newInstance();
            this.handlers.put(opcode.value(), packetHandler);
        } catch (Exception e) {
            Grasscutter.getLogger()
                    .warn("Unable to register handler {}.", handlerClass.getSimpleName(), e);
        }
    }

    public void registerHandlers(Class<? extends PacketHandler> handlerClass) {
        var handlerClasses = Grasscutter.reflector.getSubTypesOf(handlerClass);
        for (var obj : handlerClasses) {
            this.registerPacketHandler(obj);
        }

        // Debug
        Grasscutter.getLogger()
                .debug("Registered " + this.handlers.size() + " " + handlerClass.getSimpleName() + "s");
    }

    public void handle(
            GameSession session, int opcode, GameVersion version, byte[] header, byte[] payload) {
        PacketOpcodes operationCode = version.GetOperationCode(opcode);
        PacketHandler handler = this.handlers.get(operationCode);
        GeneratedMessageV3 message;

        try {
            message = version.GetMessage(operationCode).getParserForType().parseFrom(payload);
        } catch (InvalidProtocolBufferException exception) {
            Grasscutter.getLogger().error(exception.getMessage());
            exception.printStackTrace();
            return;
        }

        if (handler != null) {
            try {
                // Make sure session is ready for packets
                SessionState state = session.getState();

                if (operationCode == PacketOpcodes.PingReq) {
                    // Always continue if packet is ping request
                } else if (operationCode == PacketOpcodes.GetPlayerTokenReq) {
                    if (state != SessionState.WAITING_FOR_TOKEN) {
                        return;
                    }
                } else if (state == SessionState.ACCOUNT_BANNED) {
                    session.close();
                    return;
                } else if (operationCode == PacketOpcodes.PlayerLoginReq) {
                    if (state != SessionState.WAITING_FOR_LOGIN) {
                        return;
                    }
                } else if (operationCode == PacketOpcodes.SetPlayerBornDataReq) {
                    if (state != SessionState.PICKING_CHARACTER) {
                        return;
                    }
                } else {
                    if (state != SessionState.ACTIVE) {
                        return;
                    }
                }

                // Invoke event.
                ReceivePacketEvent event = new ReceivePacketEvent(session, opcode, payload);
                event.call();
                if (!event.isCanceled()) // If event is not canceled, continue.
                handler.handle(session, message);
                // handler.handle(session, header, event.getPacketData());
            } catch (Exception ex) {
                // TODO Remove this when no more needed
                ex.printStackTrace();
            }
            return; // Packet successfully handled
        }

        // Log unhandled packets
        if (GAME_INFO.logPackets == ServerDebugMode.MISSING
                || GAME_INFO.logPackets == ServerDebugMode.ALL) {
            Grasscutter.getLogger()
                    .info(
                            "Unhandled packet ("
                                    + opcode
                                    + "): "
                                    + emu.grasscutter.net.packet.PacketOpcodesUtils.getOpcodeName(opcode));
        }
    }
}
