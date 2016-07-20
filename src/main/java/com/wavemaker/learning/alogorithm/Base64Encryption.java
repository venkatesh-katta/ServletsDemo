package com.wavemaker.learning.alogorithm;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by venkateswarluk on 19/7/16.
 */
public class Base64Encryption {

    public static String encode(String key){
        byte[]  encodedBytes  = Base64.encodeBase64(key.getBytes());
        return new String(encodedBytes);
    }
    public static String decode(String token){
        byte[] decodedBytes = Base64.decodeBase64(token.getBytes());
        return new String(decodedBytes);
    }

}
