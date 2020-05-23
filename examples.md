```java
    public boolean sendPacket(Player player, LCPacket packet) {
        if (isRunningLunarClient(player)) {
            player.sendPluginMessage(this, LUNAR_MESSAGE_CHANNEL, LCPacket.getPacketData(packet));
            Bukkit.getPluginManager().callEvent(new LCPacketSentEvent(player, packet));
            return true;
        } else if ((isRunningCheatBreaker(player))) {
            return false;
        } else if (!playersNotRegistered.contains(player.getUniqueId())) {
            lcPacketQueue.putIfAbsent(player.getUniqueId(), new ArrayList<>());
            lcPacketQueue.get(player.getUniqueId()).add(packet);
            return false;
        }
        return false;
    }
```
