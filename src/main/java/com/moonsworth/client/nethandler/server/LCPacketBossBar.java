package com.moonsworth.client.nethandler.server;

import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketBossBar extends LCPacket
{
    private int action;
    private String text;
    private float health;

    @Override
    public void write(ByteBufWrapper b) {
        b.writeVarInt(this.action);
        if (this.action == 0) {
            b.writeString(this.text);
            b.buf().writeFloat(this.health);
        }
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.action = b.readVarInt();
        if (this.action == 0) {
            this.text = b.readString();
            this.health = b.buf().readFloat();
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleBossBar(this);
    }

    public int getAction() {
        return this.action;
    }

    public String getText() {
        return this.text;
    }

    public float getHealth() {
        return this.health;
    }

    @ConstructorProperties({ "action", "text", "health" })
    public LCPacketBossBar(int action, String text, float health) {
        this.action = action;
        this.text = text;
        this.health = health;
    }

    public LCPacketBossBar() {
    }
}
