package com.moonsworth.client.nethandler.server;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketWorldBorderUpdate extends LCPacket
{
    private String id;
    private double minX;
    private double minZ;
    private double maxX;
    private double maxZ;
    private int durationTicks;

    @Override
    public void write(ByteBufWrapper b) {
        b.writeString(this.id);
        b.buf().writeDouble(this.minX);
        b.buf().writeDouble(this.minZ);
        b.buf().writeDouble(this.maxX);
        b.buf().writeDouble(this.maxZ);
        b.buf().writeInt(this.durationTicks);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.id = b.readString();
        this.minX = b.buf().readDouble();
        this.minZ = b.buf().readDouble();
        this.maxX = b.buf().readDouble();
        this.maxZ = b.buf().readDouble();
        this.durationTicks = b.buf().readInt();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleWorldBorderUpdate(this);
    }

    public String getId() {
        return this.id;
    }

    public double getMinX() {
        return this.minX;
    }

    public double getMinZ() {
        return this.minZ;
    }

    public double getMaxX() {
        return this.maxX;
    }

    public double getMaxZ() {
        return this.maxZ;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public LCPacketWorldBorderUpdate() {
    }

    @ConstructorProperties({ "id", "minX", "minZ", "maxX", "maxZ", "durationTicks" })
    public LCPacketWorldBorderUpdate(String id, double minX, double minZ, double maxX, double maxZ, int durationTicks) {
        this.id = id;
        this.minX = minX;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxZ = maxZ;
        this.durationTicks = durationTicks;
    }
}
