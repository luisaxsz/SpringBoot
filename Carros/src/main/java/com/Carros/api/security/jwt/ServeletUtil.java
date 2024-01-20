package com.Carros.api.security.jwt;
import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ServeletUtil {
	public static void write(HttpServletResponse response, HttpStatus status, String json) throws IOException {

        response.setStatus(status.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        response.getWriter().write(json);
    }

    public static String getJson(String key, String value) {
        JsonObject json = new JsonObject();
        json.addProperty(key, value);
        return json.toString();
    }
}
