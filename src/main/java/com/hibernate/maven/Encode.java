package com.hibernate.maven;

import sun.misc.BASE64Encoder;

import java.util.Base64;

public class Encode {

    public static String encode(String a) {
        String encoded = Base64.getEncoder().encodeToString(a.getBytes());
        return encoded;
    }
}
