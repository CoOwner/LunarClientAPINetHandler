package com.moonsworth.client.nethandler.server;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;

public class LCPacketCooldown extends LCPacket
{
    private String message;
    private long durationMs;
    private int iconId;

    public LCPacketCooldown() {
    }

    public LCPacketCooldown(String message, long durationMs, int iconId) {
        this.message = message;
        this.durationMs = durationMs;
        this.iconId = iconId;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeString(this.message);
        b.buf().writeLong(this.durationMs);
        b.buf().writeInt(this.iconId);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.message = b.readString();
        this.durationMs = b.buf().readLong();
        this.iconId = b.buf().readInt();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleCooldown(this);
    }

    public String getMessage() {
        return this.message;
    }

    public long getDurationMs() {
        return this.durationMs;
    }

    public int getIconId() {
        return this.iconId;
    }
}
