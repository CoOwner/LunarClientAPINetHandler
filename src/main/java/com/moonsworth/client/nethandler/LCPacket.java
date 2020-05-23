package com.moonsworth.client.nethandler;

import java.io.*;
import io.netty.buffer.*;
import com.google.common.collect.*;
import com.moonsworth.client.nethandler.client.*;
import com.moonsworth.client.nethandler.server.*;
import com.moonsworth.client.nethandler.shared.*;

public abstract class LCPacket
{
    private static BiMap<Class, Integer> REGISTRY;
    private Object attachment;

    public static LCPacket handle(byte[] data) {
        return handle(data, null);
    }

    public static LCPacket handle(byte[] data, Object attachment) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.wrappedBuffer(data));
        int packetId = wrappedBuffer.readVarInt();
        Class packetClass = LCPacket.REGISTRY.inverse().get(packetId);
        if (packetClass != null) {
            try {
                LCPacket packet = (LCPacket) packetClass.newInstance();
                packet.attach(attachment);
                packet.read(wrappedBuffer);
                return packet;
            }
            catch (IOException | InstantiationException | IllegalAccessException ex2) {
                ex2.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] getPacketData(LCPacket packet) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.buffer());
        wrappedBuffer.writeVarInt(LCPacket.REGISTRY.get(packet.getClass()));
        try {
            packet.write(wrappedBuffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return wrappedBuffer.buf().array();
    }

    public static ByteBuf getPacketBuf(LCPacket packet) {
        ByteBufWrapper wrappedBuffer = new ByteBufWrapper(Unpooled.buffer());
        wrappedBuffer.writeVarInt(LCPacket.REGISTRY.get(packet.getClass()));
        try {
            packet.write(wrappedBuffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return wrappedBuffer.buf();
    }

    private static void addPacket(int id, Class clazz) {
        if (LCPacket.REGISTRY.containsKey(clazz)) {
            throw new IllegalArgumentException("Duplicate packet class (" + clazz.getSimpleName() + "), already used by " + LCPacket.REGISTRY.get(clazz));
        }
        if (LCPacket.REGISTRY.containsValue(id)) {
            throw new IllegalArgumentException("Duplicate packet ID (" + id + "), already used by " + LCPacket.REGISTRY.inverse().get(id).getSimpleName());
        }
        LCPacket.REGISTRY.put(clazz, id);
    }

    public abstract void write(ByteBufWrapper p0) throws IOException;

    public abstract void read(ByteBufWrapper p0) throws IOException;

    public abstract void process(ILCNetHandler p0);

    protected void writeBlob(ByteBufWrapper b, byte[] bytes) {
        b.buf().writeShort(bytes.length);
        b.buf().writeBytes(bytes);
    }

    protected byte[] readBlob(ByteBufWrapper b) {
        short key = b.buf().readShort();
        if (key < 0) {
            System.out.println("Key was smaller than nothing!  Weird key!");
            return null;
        }
        byte[] blob = new byte[key];
        b.buf().readBytes(blob);
        return blob;
    }

    public <T> void attach(T obj) {
        this.attachment = obj;
    }

    public <T> T getAttachment() {
        return (T)this.attachment;
    }

    static {
        REGISTRY = HashBiMap.create();
        addPacket(0, LCPacketClientVoice.class);
        addPacket(1, LCPacketVoiceChannelSwitch.class);
        addPacket(2, LCPacketVoiceMute.class);
        addPacket(27, LCPacketVersionNumber.class);
        addPacket(26, LCPacketEmoteBroadcast.class);
        addPacket(3, LCPacketCooldown.class);
        addPacket(4, LCPacketHologram.class);
        addPacket(6, LCPacketHologramRemove.class);
        addPacket(5, LCPacketHologramUpdate.class);
        addPacket(7, LCPacketNametagsOverride.class);
        addPacket(8, LCPacketNametagsUpdate.class);
        addPacket(9, LCPacketNotification.class);
        addPacket(10, LCPacketServerRule.class);
        addPacket(11, LCPacketServerUpdate.class);
        addPacket(12, LCPacketStaffModState.class);
        addPacket(13, LCPacketTeammates.class);
        addPacket(14, LCPacketTitle.class);
        addPacket(15, LCPacketUpdateWorld.class);
        addPacket(16, LCPacketVoice.class);
        addPacket(17, LCPacketVoiceChannel.class);
        addPacket(18, LCPacketVoiceChannelRemove.class);
        addPacket(19, LCPacketVoiceChannelUpdate.class);
        addPacket(20, LCPacketWorldBorder.class);
        addPacket(21, LCPacketWorldBorderRemove.class);
        addPacket(22, LCPacketWorldBorderUpdate.class);
        addPacket(25, LCPacketGhost.class);
        addPacket(23, LCPacketWaypointAdd.class);
        addPacket(24, LCPacketWaypointRemove.class);
    }
}
