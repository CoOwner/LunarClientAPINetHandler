package com.moonsworth.client.nethandler.server;

import java.util.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;

public class LCPacketHologramRemove extends LCPacket
{
    private UUID uuid;

    public LCPacketHologramRemove() {
    }

    public LCPacketHologramRemove(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeUUID(this.uuid);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.uuid = b.readUUID();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleRemoveHologram(this);
    }

    public UUID getUuid() {
        return this.uuid;
    }
}
