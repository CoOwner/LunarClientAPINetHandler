package com.moonsworth.client.nethandler.server;

import java.util.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketVoice extends LCPacket
{
    private UUID uuid;
    private byte[] data;

    @Override
    public void write(ByteBufWrapper b) {
        b.writeUUID(this.uuid);
        this.writeBlob(b, this.data);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.uuid = b.readUUID();
        this.data = this.readBlob(b);
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleVoice(this);
    }

    @ConstructorProperties({ "uuid", "data" })
    public LCPacketVoice(UUID uuid, byte[] data) {
        this.uuid = uuid;
        this.data = data;
    }

    public LCPacketVoice() {
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public byte[] getData() {
        return this.data;
    }
}
