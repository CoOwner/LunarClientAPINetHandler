package com.moonsworth.client.nethandler.server;

import java.io.*;
import java.util.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;
import java.beans.*;

public class LCPacketTeammates extends LCPacket
{
    private UUID leader;
    private long lastMs;
    private Map<UUID, Map<String, Double>> players;

    @Override
    public void write(ByteBufWrapper b) {
        b.buf().writeBoolean(this.leader != null);
        if (this.leader != null) {
            b.writeUUID(this.leader);
        }
        b.buf().writeLong(this.lastMs);
        b.writeVarInt(this.players.values().size());
        for (Map.Entry<UUID, Map<String, Double>> entry : this.players.entrySet()) {
            b.writeUUID(entry.getKey());
            b.writeVarInt(entry.getValue().values().size());
            for (Map.Entry<String, Double> entry2 : entry.getValue().entrySet()) {
                b.writeString(entry2.getKey());
                b.buf().writeDouble(entry2.getValue());
            }
        }
    }

    @Override
    public void read(ByteBufWrapper b) {
        boolean hasLeader = b.buf().readBoolean();
        if (hasLeader) {
            this.leader = b.readUUID();
        }
        this.lastMs = b.buf().readLong();
        int playersSize = b.readVarInt();
        this.players = new HashMap<>();
        for (int i = 0; i < playersSize; ++i) {
            UUID uuid = b.readUUID();
            int posMapSize = b.readVarInt();
            Map<String, Double> posMap = new HashMap<>();
            for (int j = 0; j < posMapSize; ++j) {
                String key = b.readString();
                double val = b.buf().readDouble();
                posMap.put(key, val);
            }
            this.players.put(uuid, posMap);
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleTeammates(this);
    }

    public UUID getLeader() {
        return this.leader;
    }

    public long getLastMs() {
        return this.lastMs;
    }

    public Map<UUID, Map<String, Double>> getPlayers() {
        return this.players;
    }

    @ConstructorProperties({ "leader", "lastMs", "players" })
    public LCPacketTeammates(UUID leader, long lastMs, Map<UUID, Map<String, Double>> players) {
        this.leader = leader;
        this.lastMs = lastMs;
        this.players = players;
    }

    public LCPacketTeammates() {
    }
}
