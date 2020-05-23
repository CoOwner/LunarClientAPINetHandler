package com.moonsworth.client.nethandler.client;

import java.util.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.server.*;

public class LCPacketVoiceMute extends LCPacket
{
    private UUID muting;

    public LCPacketVoiceMute() {
    }

    public LCPacketVoiceMute(UUID muting) {
        this.muting = muting;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeUUID(this.muting);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.muting = b.readUUID();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerServer)handler).handleVoiceMute(this);
    }

    public UUID getMuting() {
        return this.muting;
    }
}
