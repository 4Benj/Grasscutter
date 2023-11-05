package emu.grasscutter.server.game.version;

import com.google.protobuf.GeneratedMessageV3;
import emu.grasscutter.net.packet.PacketOpcodes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameVersion {
    private final List<String> gameVersion;
    private final Map<Integer, PacketOpcodes> operationCodes;
    private final Map<PacketOpcodes, Integer> opcodesIntegerMap;
    private final Map<PacketOpcodes, GeneratedMessageV3> protoBuilder;

    public GameVersion(
            List<String> gameVersion,
            Map<Integer, PacketOpcodes> operationCodes,
            Map<PacketOpcodes, GeneratedMessageV3> protoDefinitions) {
        this.gameVersion = gameVersion;
        this.protoBuilder = protoDefinitions;
        this.operationCodes = operationCodes;
        opcodesIntegerMap = new HashMap<>();
        operationCodes.keySet().stream()
                .forEach(
                        integer -> {
                            opcodesIntegerMap.put(operationCodes.get(integer), integer);
                        });
    }

    public List<String> getGameVersions() {
        return gameVersion;
    }

    public PacketOpcodes GetOperationCode(int opCode) {
        return operationCodes.get(opCode);
    }

    public int GetOperationCode(PacketOpcodes opCode) {
        return opcodesIntegerMap.get(opCode);
    }

    public GeneratedMessageV3 GetMessage(PacketOpcodes opCode) {
        return protoBuilder.get(opCode);
    }
}
