package com.ha.ltschat.util;

import java.util.UUID;

public class UuidUtil {
    public static String generateUuid(int length) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, Math.min(length, uuid.length()));
    }
}
