package com.moonsworth.client.nethandler.client;

import java.util.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;

public class LCPacketEmoteBroadcast extends LCPacket
{
    private UUID uuid;
    private int emoteId;

    public LCPacketEmoteBroadcast() {
    }

    public LCPacketEmoteBroadcast(UUID broadcastTo, int emoteId) {
        this.uuid = broadcastTo;
        this.emoteId = emoteId;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeUUID(this.uuid);
        b.buf().writeInt(this.emoteId);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.uuid = b.readUUID();
        this.emoteId = b.buf().readInt();
    }

    @Override
    public void process(ILCNetHandler handler) {
        handler.handleEmote(this);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public int getEmoteId() {
        return this.emoteId;
    }
}
