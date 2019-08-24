package sda.pl.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import sda.pl.dto.WydajDecDto;

import java.io.File;
import java.io.IOException;
import java.util.Currency;

public class JsonToObject {

    public WydajDecDto getJsontoWydajDec(File json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WydajDecDto WydajDec = objectMapper.readValue(json, WydajDecDto.class);
        return WydajDec;
    }
    private WydajDecDto mapJSONObjectToCurrencyObject(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, WydajDecDto.class);
    }
}
