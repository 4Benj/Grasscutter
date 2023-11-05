package emu.grasscutter.net.packet;

import com.google.protobuf.GeneratedMessageV3;
import emu.grasscutter.server.game.GameSession;

public abstract class PacketHandler {
    protected static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    public abstract void handle(GameSession session, GeneratedMessageV3 message) throws Exception;
}
