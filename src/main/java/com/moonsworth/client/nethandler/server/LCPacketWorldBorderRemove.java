package com.moonsworth.client.nethandler.server;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketWorldBorderRemove extends LCPacket
{
    private String id;

    @Override
    public void write(ByteBufWrapper b) {
        b.writeString(this.id);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.id = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleWorldBorderRemove(this);
    }

    @ConstructorProperties({ "id" })
    public LCPacketWorldBorderRemove(String id) {
        this.id = id;
    }

    public LCPacketWorldBorderRemove() {
    }

    public String getId() {
        return this.id;
    }
}
