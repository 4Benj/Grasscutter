package io.grasscutter.protocoltranslation.protos;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import io.grasscutter.protocoltranslation.protocol.Version;

public abstract class ProtocolMessage {
    public abstract Message Serialize(Version gameVersion);
    //public static ProtocolMessage Deserialize(Version gameVersion, GeneratedMessageV3 message);

    protected static void setField(GeneratedMessageV3.Builder messageBuilder, Descriptors.Descriptor descriptor, String name, Object value) {
        Descriptors.FieldDescriptor fieldDescriptor = descriptor.findFieldByName(name);

        if(fieldDescriptor != null) {
            messageBuilder.setField(fieldDescriptor, value);
            return;
        }

        // If null then it might not exist in this game version or there is a spelling mistake in either the OuterClass
        // or the proto interface
    }

    protected static Object getField(GeneratedMessageV3 messageBuilder, Descriptors.Descriptor descriptor, String name) {
        Descriptors.FieldDescriptor fieldDescriptor = descriptor.findFieldByName(name);

        if(fieldDescriptor != null) {
            return messageBuilder.getField(fieldDescriptor);
        }

        // If null then it might not exist in this game version or there is a spelling mistake in either the OuterClass
        // or the proto interface
        return null;
    }
}
