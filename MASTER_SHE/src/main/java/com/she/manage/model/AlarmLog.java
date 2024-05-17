package com.she.manage.model;

import lombok.Data;

@Data
public class AlarmLog {
    private int logNo;

    private String senderId;

    private String senderPhoneNo;

    private String receiverId;

    private String receiverPhoneNo;

    private String contents;

    private String sendDt;

    private String sendYn;

    private String failDesc;

    private int tryCount;

    private String tryDt;

}
