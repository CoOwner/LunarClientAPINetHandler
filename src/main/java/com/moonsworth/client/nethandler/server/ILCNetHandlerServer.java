package com.moonsworth.client.nethandler.server;

import com.moonsworth.client.nethandler.*;
import com.moonsworth.client.nethandler.client.*;

public interface ILCNetHandlerServer extends ILCNetHandler
{
    void handleVoice(LCPacketClientVoice p0);

    void handleVoiceChannelSwitch(LCPacketVoiceChannelSwitch p0);

    void handleVoiceMute(LCPacketVoiceMute p0);

    void handlePacketVersionNumber(LCPacketVersionNumber p0);

    void handleStaffModStatus(LCPacketStaffModStatus p0);
}
