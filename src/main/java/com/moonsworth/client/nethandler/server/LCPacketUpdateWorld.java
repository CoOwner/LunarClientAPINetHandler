package com.moonsworth.client.nethandler.server;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketUpdateWorld extends LCPacket
{
    private String world;

    @Override
    public void write(ByteBufWrapper b) {
        b.writeString(this.world);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.world = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleUpdateWorld(this);
    }

    @ConstructorProperties({ "world" })
    public LCPacketUpdateWorld(String world) {
        this.world = world;
    }

    public LCPacketUpdateWorld() {
    }

    public String getWorld() {
        return this.world;
    }
}
