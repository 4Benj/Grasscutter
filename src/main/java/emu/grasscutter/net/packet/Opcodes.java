package emu.grasscutter.net.packet;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Opcodes {
    /** Opcode for the packet/handler */
    PacketOpcodes value();

    /** HANDLER ONLY - will disable this handler from being registered */
    boolean disabled() default false;
}
