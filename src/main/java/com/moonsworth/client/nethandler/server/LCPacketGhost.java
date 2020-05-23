package com.moonsworth.client.nethandler.server;

import java.io.*;
import java.util.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;

public class LCPacketGhost extends LCPacket
{
    private List<UUID> addGhostList;
    private List<UUID> removeGhostList;

    public LCPacketGhost() {
    }


    public LCPacketGhost(List<UUID> uuidList, List<UUID> removeGhostList) {
        this.addGhostList = uuidList;
        this.removeGhostList = removeGhostList;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeVarInt(this.addGhostList.size());
        for (UUID uuid : this.addGhostList) {
            b.writeUUID(uuid);
        }
        b.writeVarInt(this.removeGhostList.size());
        for (UUID uuid : this.removeGhostList) {
            b.writeUUID(uuid);
        }
    }

    @Override
    public void read(ByteBufWrapper b) {
        int addListSize = b.readVarInt();
        this.addGhostList = new ArrayList<>();
        for (int i = 0; i < addListSize; ++i) {
            this.addGhostList.add(b.readUUID());
        }
        int removeListSize = b.readVarInt();
        this.removeGhostList = new ArrayList<>();
        for (int j = 0; j < removeListSize; ++j) {
            this.removeGhostList.add(b.readUUID());
        }
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerClient)handler).handleGhost(this);
    }

    public List<UUID> getAddGhostList() {
        return this.addGhostList;
    }

    public List<UUID> getRemoveGhostList() {
        return this.removeGhostList;
    }
}
