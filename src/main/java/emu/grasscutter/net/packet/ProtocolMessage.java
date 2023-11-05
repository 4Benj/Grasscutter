package emu.grasscutter.net.packet;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import emu.grasscutter.server.game.version.GameVersion;

public abstract class ProtocolMessage {
    public abstract Message Serialize(GameVersion gameVersion);
    //public abstract ProtocolMessage Deserialize(GameVersion gameVersion, Message message);

    protected static void setField(Message.Builder messageBuilder, Descriptors.Descriptor descriptor, String name, Object value) {
        Descriptors.FieldDescriptor fieldDescriptor = descriptor.findFieldByName(name);

        if(fieldDescriptor != null) {
            messageBuilder.setField(fieldDescriptor, value);
            return;
        }

        // If null then it might not exist in this game version or there is a spelling mistake in either the OuterClass
        // or the proto interface
    }

    protected static Object getField(Message messageBuilder, Descriptors.Descriptor descriptor, String name) {
        Descriptors.FieldDescriptor fieldDescriptor = descriptor.findFieldByName(name);

        if(fieldDescriptor != null) {
            return messageBuilder.getField(fieldDescriptor);
        }

        // If null then it might not exist in this game version or there is a spelling mistake in either the OuterClass
        // or the proto interface
        return null;
    }
}
