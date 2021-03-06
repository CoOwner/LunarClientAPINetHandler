package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.obj.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;

public class LCPacketServerRule extends LCPacket
{
    private ServerRule rule;
    private int intValue;
    private float floatValue;
    private boolean booleanValue;
    private String stringValue;

    public LCPacketServerRule() {
        this.stringValue = "";
    }

    public LCPacketServerRule(ServerRule rule, float value) {
        this(rule);
        this.floatValue = value;
    }

    public LCPacketServerRule(ServerRule rule, boolean value) {
        this(rule);
        this.booleanValue = value;
    }

    public LCPacketServerRule(ServerRule rule, int value) {
        this(rule);
        this.intValue = value;
    }

    public LCPacketServerRule(ServerRule rule, String value) {
        this(rule);
        this.stringValue = value;
    }

    private LCPacketServerRule(ServerRule rule) {
        this.stringValue = "";
        this.rule = rule;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeString(this.rule.getRule());
        b.buf().writeBoolean(this.booleanValue);
        b.buf().writeInt(this.intValue);
        b.buf().writeFloat(this.floatValue);
        b.writeString(this.stringValue);
    }

    @Override
    protected byte[] readBlob(ByteBufWrapper b) {
        return super.readBlob(b);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.rule = ServerRule.getRule(b.readString());
        this.booleanValue = b.buf().readBoolean();
        this.intValue = b.buf().readInt();
        this.floatValue = b.buf().readFloat();
        this.stringValue = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleServerRule(this);
    }

    public ServerRule getRule() {
        return this.rule;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public float getFloatValue() {
        return this.floatValue;
    }

    public boolean isBooleanValue() {
        return this.booleanValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
