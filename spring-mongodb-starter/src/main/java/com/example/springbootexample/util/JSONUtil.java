package com.example.springbootexample.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JSONUtil {
    public static String getSHA1(byte[] data) {
        return DigestUtils.sha1Hex(data);
    }

    public static void generateJSONResponse(HttpServletResponse response, Object obj) throws IOException {
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, obj);
    }

}
