package emu.grasscutter.server.game.version;

import com.google.protobuf.GeneratedMessageV3;

import java.util.Map;

public class GameVersion {
    private final String gameVersion;
    private final Map<PacketOpcode, Integer> operationCodes;
    private final Map<PacketOpcode, GeneratedMessageV3.Builder> protoBuilderDefinitions;

    public GameVersion(String gameVersion, Map<PacketOpcode, Integer> operationCodes, Map<PacketOpcode, GeneratedMessageV3.Builder> protoDefinitions) {
        this.gameVersion = gameVersion;
        this.operationCodes = operationCodes;
        this.protoBuilderDefinitions = protoDefinitions;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public int GetOperationCode(PacketOpcode opCode) {
        return operationCodes.get(opCode);
    }

    public GeneratedMessageV3.Builder GetMessageBuilder(PacketOpcode opCode) {
        return protoBuilderDefinitions.get(opCode);
    }
}
