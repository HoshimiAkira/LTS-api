package com.ha.ltschat.util;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UuidUtil {
    public static String generateUuid(int length) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, Math.min(length, uuid.length()));
    }
}
