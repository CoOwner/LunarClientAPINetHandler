package com.moonsworth.client.nethandler.client;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.server.*;

public class LCPacketVersionNumber extends LCPacket
{
    private String currentVersionNumber;

    public LCPacketVersionNumber() {
    }

    public LCPacketVersionNumber(String currentVersionNumber) {
        this.currentVersionNumber = currentVersionNumber;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeString(this.currentVersionNumber);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.currentVersionNumber = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerServer)handler).handlePacketVersionNumber(this);
    }

    public String getCurrentVersionNumber() {
        return this.currentVersionNumber;
    }
}
