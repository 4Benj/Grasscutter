package emu.grasscutter.net.packet.general;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import emu.grasscutter.net.packet.ProtocolMessage;
import emu.grasscutter.server.game.version.GameVersion;
import java.util.Map;

public class PacketHead {
    private int packetId;
    private int rpcId;
    private int clientSequenceId;
    private int enetChannelId;
    private int enetIsReliable;
    private long sentMs;
    private int userId;
    private int userIp;
    private int userSessionId;
    private long recvTimeMs;
    private int rpcBeginTimeMs;
    private Map<Integer, Integer> extMap;
    private int senderAppId;
    private int sourceService;
    private int targetService;
    private Map<Integer, Integer> serviceAppIdMap;
    private boolean isSetGameThread;
    private int gameThreadIndex;

    public PacketHead setClientSequenceId(int clientSequenceId) {
        this.clientSequenceId = clientSequenceId;
        return this;
    }

    public PacketHead setSentMs(long sentMs) {
        this.sentMs = sentMs;
        return this;
    }

    public Message Serialize() {
        Descriptors.Descriptor descriptor = createDescriptor();
        DynamicMessage.Builder dynamicBuilder = DynamicMessage.newBuilder(descriptor);

        dynamicBuilder.setField(descriptor.findFieldByName("packet_id"), packetId);
        dynamicBuilder.setField(descriptor.findFieldByName("rpc_id"), rpcId);
        dynamicBuilder.setField(descriptor.findFieldByName("client_sequence_id"), clientSequenceId);
        dynamicBuilder.setField(descriptor.findFieldByName("enet_channel_id"), enetChannelId);
        dynamicBuilder.setField(descriptor.findFieldByName("enet_is_reliable"), enetIsReliable);
        dynamicBuilder.setField(descriptor.findFieldByName("sent_ms"), sentMs);
        dynamicBuilder.setField(descriptor.findFieldByName("user_id"), userId);
        dynamicBuilder.setField(descriptor.findFieldByName("user_ip"), userIp);
        dynamicBuilder.setField(descriptor.findFieldByName("user_session_id"), userSessionId);
        dynamicBuilder.setField(descriptor.findFieldByName("recv_time_ms"), recvTimeMs);
        dynamicBuilder.setField(descriptor.findFieldByName("rpc_begin_time_ms"), rpcBeginTimeMs);
        dynamicBuilder.setField(descriptor.findFieldByName("ext_map"), extMap);
        dynamicBuilder.setField(descriptor.findFieldByName("sender_app_id"), senderAppId);
        dynamicBuilder.setField(descriptor.findFieldByName("source_service"), sourceService);
        dynamicBuilder.setField(descriptor.findFieldByName("target_service"), targetService);
        dynamicBuilder.setField(descriptor.findFieldByName("service_app_id_map"), serviceAppIdMap);
        dynamicBuilder.setField(descriptor.findFieldByName("is_set_game_thread"), isSetGameThread);
        dynamicBuilder.setField(descriptor.findFieldByName("game_thread_index"), gameThreadIndex);

        return dynamicBuilder.build();
    }

    public ProtocolMessage Deserialize(GameVersion gameVersion, Message message) {
        // TODO
        return null;
    }

    public static Descriptors.Descriptor createDescriptor() {
        // TODO
        return null;
    }
}
