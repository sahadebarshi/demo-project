package com.example.demo.util;

import java.util.Base64;

public class EncoderDecoder {

    public static String getEncodedParam(String sqlParam){
        return Base64.getEncoder().encodeToString(sqlParam.getBytes());
    }

    public static String getDecodedParam(String sqlParam){
        return new String(Base64.getDecoder().decode(sqlParam));
    }
}
