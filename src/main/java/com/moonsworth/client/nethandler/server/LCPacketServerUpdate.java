package com.moonsworth.client.nethandler.server;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketServerUpdate extends LCPacket
{
    private String server;

    @Override
    public void write(ByteBufWrapper b) {
        b.writeString(this.server);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.server = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleServerUpdate(this);
    }

    @ConstructorProperties({ "server" })
    public LCPacketServerUpdate(String server) {
        this.server = server;
    }

    public LCPacketServerUpdate() {
    }

    public String getServer() {
        return this.server;
    }
}
