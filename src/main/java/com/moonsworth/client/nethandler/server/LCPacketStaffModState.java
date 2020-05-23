package com.moonsworth.client.nethandler.server;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketStaffModState extends LCPacket
{
    private String mod;
    private boolean state;

    @Override
    public void write(ByteBufWrapper b) {
        b.writeString(this.mod);
        b.buf().writeBoolean(this.state);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.mod = b.readString();
        this.state = b.buf().readBoolean();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleStaffModState(this);
    }

    public String getMod() {
        return this.mod;
    }

    public boolean isState() {
        return this.state;
    }

    @ConstructorProperties({ "mod", "state" })
    public LCPacketStaffModState(String mod, boolean state) {
        this.mod = mod;
        this.state = state;
    }

    public LCPacketStaffModState() {
    }
}
