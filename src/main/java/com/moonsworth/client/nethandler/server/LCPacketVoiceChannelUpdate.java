package com.moonsworth.client.nethandler.server;

import java.util.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketVoiceChannelUpdate extends LCPacket
{
    public int status;
    private UUID channelUuid;
    private UUID uuid;
    private String name;

    @Override
    public void write(ByteBufWrapper b) {
        b.writeVarInt(this.status);
        b.writeUUID(this.channelUuid);
        b.writeUUID(this.uuid);
        b.writeString(this.name);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.status = b.readVarInt();
        this.channelUuid = b.readUUID();
        this.uuid = b.readUUID();
        this.name = b.readString();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleVoiceChannelUpdate(this);
    }

    @ConstructorProperties({ "status", "channelUuid", "uuid", "name" })
    public LCPacketVoiceChannelUpdate(int status, UUID channelUuid, UUID uuid, String name) {
        this.status = status;
        this.channelUuid = channelUuid;
        this.uuid = uuid;
        this.name = name;
    }

    public LCPacketVoiceChannelUpdate() {
    }

    public int getStatus() {
        return this.status;
    }

    public UUID getChannelUuid() {
        return this.channelUuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }
}
