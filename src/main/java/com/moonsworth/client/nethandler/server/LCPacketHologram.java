package com.moonsworth.client.nethandler.server;

import java.io.*;
import java.util.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;

public class LCPacketHologram extends LCPacket
{
    private UUID uuid;
    private double x;
    private double y;
    private double z;
    private List<String> lines;

    public LCPacketHologram() {
    }

    public LCPacketHologram(UUID uuid, double x, double y, double z, List<String> lines) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.lines = lines;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeUUID(this.uuid);
        b.buf().writeDouble(this.x);
        b.buf().writeDouble(this.y);
        b.buf().writeDouble(this.z);
        b.writeVarInt(this.lines.size());
        for (String s : this.lines) {
            b.writeString(s);
        }
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.uuid = b.readUUID();
        this.x = b.buf().readDouble();
        this.y = b.buf().readDouble();
        this.z = b.buf().readDouble();
        int linesSize = b.readVarInt();
        this.lines = new ArrayList<String>();
        for (int i = 0; i < linesSize; ++i) {
            this.lines.add(b.readString());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleAddHologram(this);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public List<String> getLines() {
        return this.lines;
    }
}
