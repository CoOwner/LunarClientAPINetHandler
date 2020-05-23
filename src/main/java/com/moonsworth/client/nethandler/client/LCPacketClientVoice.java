package com.moonsworth.client.nethandler.client;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.server.*;

public class LCPacketClientVoice extends LCPacket
{
    private byte[] data;

    public LCPacketClientVoice() {
    }

    public LCPacketClientVoice(byte[] data) {
        this.data = data;
    }

    @Override
    public void write(ByteBufWrapper b) {
        this.writeBlob(b, this.data);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.data = this.readBlob(b);
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerServer)handler).handleVoice(this);
    }

    public byte[] getData() {
        return this.data;
    }
}
