package emu.grasscutter.server.packet.recv;

import com.google.protobuf.GeneratedMessageV3;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.server.game.GameSession;

@Opcodes(PacketOpcodes.NONE)
public class Handler extends PacketHandler {

    @Override
    public void handle(GameSession session, GeneratedMessageV3 message) throws Exception {
        // Auto template
    }
}
