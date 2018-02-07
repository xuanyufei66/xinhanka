package com.payease.wallet.dto.bean;


import java.io.Serializable;

/**
 * Created by zhangzhili on 2017/11/2.
 */
public class RequestBo implements Serializable {


    private PacketHead packetHead;

    private String packetBody;

    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public PacketHead getPacketHead() {
        return packetHead;
    }

    public void setPacketHead(PacketHead packetHead) {
        this.packetHead = packetHead;
    }

    public String getPacketBody() {
        return packetBody;
    }

    public void setPacketBody(String packetBody) {
        this.packetBody = packetBody;
    }
}
