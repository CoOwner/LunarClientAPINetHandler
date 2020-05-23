package com.moonsworth.client.nethandler.server;

import java.io.*;
import java.util.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;

public class LCPacketHologramUpdate extends LCPacket
{
    private UUID uuid;
    private List<String> lines;

    public LCPacketHologramUpdate() {
    }

    public LCPacketHologramUpdate(UUID uuid, List<String> lines) {
        this.uuid = uuid;
        this.lines = lines;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeUUID(this.uuid);
        b.writeVarInt(this.lines.size());
        for (String s : this.lines) {
            b.writeString(s);
        }
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.uuid = b.readUUID();
        int linesSize = b.readVarInt();
        this.lines = new ArrayList<String>();
        for (int i = 0; i < linesSize; ++i) {
            this.lines.add(b.readString());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleUpdateHologram(this);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public List<String> getLines() {
        return this.lines;
    }
}
