package emu.grasscutter.net.packet.general;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.packet.ProtocolMessage;
import emu.grasscutter.server.game.version.GameVersion;

public class StopServerInfo extends ProtocolMessage {
    private int stopBeginTime;
    private int stopEndTime;
    private String url;
    private String contentMsg;

    @Override
    public Message Serialize(GameVersion gameVersion) {
        Message.Builder messageBuilder =
                gameVersion.GetMessage(PacketOpcodes.GetPlayerTokenReq).toBuilder();

        if (messageBuilder == null) {
            // Handle the case where the message builder is not found for the specified OpCode.
            return null;
        }

        // Access the descriptor for each field by name.
        Descriptors.Descriptor descriptor = messageBuilder.getDescriptorForType();

        setField(messageBuilder, descriptor, "stop_begin_time", stopBeginTime);
        setField(messageBuilder, descriptor, "stop_end_time", stopEndTime);
        setField(messageBuilder, descriptor, "url", url);
        setField(messageBuilder, descriptor, "content_msg", contentMsg);

        // Build the dynamic message.
        Message message = messageBuilder.build();

        return message;
    }

    public StopServerInfo Deserialize(GameVersion gameVersion, Message message) {
        if (message == null) {
            // Handle the case where the message is null or incompatible.
            return null;
        }

        // Access the descriptor for each field by name.
        Descriptors.Descriptor descriptor = message.getDescriptorForType();

        StopServerInfo instance = new StopServerInfo();

        instance.stopBeginTime = (int) getField(message, descriptor, "stop_begin_time");
        instance.stopEndTime = (int) getField(message, descriptor, "stop_end_time");
        instance.url = (String) getField(message, descriptor, "url");
        instance.contentMsg = (String) getField(message, descriptor, "content_msg");

        return instance;
    }

    // Getters
    public int getStopBeginTime() {
        return stopBeginTime;
    }

    public int getStopEndTime() {
        return stopEndTime;
    }

    public String getUrl() {
        return url;
    }

    public String getContentMsg() {
        return contentMsg;
    }

    // Setters
    public void setStopBeginTime(int stopBeginTime) {
        this.stopBeginTime = stopBeginTime;
    }

    public void setStopEndTime(int stopEndTime) {
        this.stopEndTime = stopEndTime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContentMsg(String contentMsg) {
        this.contentMsg = contentMsg;
    }
}
