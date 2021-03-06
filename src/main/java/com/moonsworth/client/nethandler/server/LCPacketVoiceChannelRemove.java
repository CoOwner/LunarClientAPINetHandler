package com.moonsworth.client.nethandler.server;

import java.util.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketVoiceChannelRemove extends LCPacket
{
    private UUID uuid;

    @Override
    public void write(ByteBufWrapper b) throws IOException {
        b.writeUUID(this.uuid);
    }

    @Override
    public void read(ByteBufWrapper b) throws IOException {
        this.uuid = b.readUUID();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleVoiceChannelDelete(this);
    }

    @ConstructorProperties({ "uuid" })
    public LCPacketVoiceChannelRemove(UUID uuid) {
        this.uuid = uuid;
    }

    public LCPacketVoiceChannelRemove() {
    }

    public UUID getUuid() {
        return this.uuid;
    }
}
