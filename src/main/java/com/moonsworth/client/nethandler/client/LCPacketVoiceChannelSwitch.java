package com.moonsworth.client.nethandler.client;

import java.util.*;
import java.io.*;
import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.server.*;

public class LCPacketVoiceChannelSwitch extends LCPacket
{
    private UUID switchingTo;

    public LCPacketVoiceChannelSwitch() {
    }

    public LCPacketVoiceChannelSwitch(UUID switchingTo) {
        this.switchingTo = switchingTo;
    }

    @Override
    public void write(ByteBufWrapper b) {
        b.writeUUID(this.switchingTo);
    }

    @Override
    public void read(ByteBufWrapper b) {
        this.switchingTo = b.readUUID();
    }

    @Override
    public void process(ILCNetHandler handler) {
        ((ILCNetHandlerServer)handler).handleVoiceChannelSwitch(this);
    }

    public UUID getSwitchingTo() {
        return this.switchingTo;
    }
}
