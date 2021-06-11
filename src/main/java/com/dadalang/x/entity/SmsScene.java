package com.dadalang.x.entity;

import lombok.Data;

public enum SmsScene {
    login(1),
    resetPassword(2);

    private final int raw;

    public int getRaw() {
        return raw;
    }

    SmsScene(int i) {
        raw = i;
    }

    public static SmsScene form(int value) {
        switch (value) {
            case 1:
                return SmsScene.login;
            case 2:
                return SmsScene.resetPassword;
            default:
                throw new RuntimeException("无效的值转换为SmsScene");
        }
    }
}
