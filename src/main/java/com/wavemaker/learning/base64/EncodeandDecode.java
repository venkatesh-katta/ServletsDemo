package com.wavemaker.learning.base64;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by venkateswarluk on 19/7/16.
 */
public class EncodeandDecode {
    public static String base64Encode(String token){
        byte[]  encodedBytes  = Base64.encodeBase64(token.getBytes());
        return new String(encodedBytes);
    }
    public static String base64Decode(String token){
        byte[] decodedBytes = Base64.decodeBase64(token.getBytes());
        return new String(decodedBytes);
    }
}
