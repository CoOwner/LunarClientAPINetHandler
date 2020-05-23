package com.moonsworth.client.nethandler;

import com.moonsworth.client.nethandler.shared.*;
import com.moonsworth.client.nethandler.client.*;

public interface ILCNetHandler
{
    void handleAddWaypoint(LCPacketWaypointAdd p0);

    void handleRemoveWaypoint(LCPacketWaypointRemove p0);

    void handleEmote(LCPacketEmoteBroadcast p0);
}
