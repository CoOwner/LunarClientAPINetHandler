package com.moonsworth.client.nethandler.client;

import java.util.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.server.*;

public class LCPacketStaffModStatus extends LCPacket
{
    private Set<String> enabled;

    public LCPacketStaffModStatus(Set<String> enabled) {
        this.enabled = enabled;
    }

    public LCPacketStaffModStatus() {
        this.enabled = new HashSet<String>();
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeVarInt(this.enabled.size());
        for (String s : this.enabled) {
            b.writeString(s);
        }
    }

    @Override
    public void read(ByteBufWrapper b) {
        for (int i = 0; i < b.readVarInt(); ++i) {
            this.enabled.add(b.readString());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerServer)handler).handleStaffModStatus(this);
    }

    public Set<String> getEnabled() {
        return this.enabled;
    }
}
