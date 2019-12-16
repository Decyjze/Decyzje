package sda.pl.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import sda.pl.dto.BlankietDto;
import sda.pl.dto.TabliceDto;
import sda.pl.dto.UchylDecyzje;
import sda.pl.dto.WydajDecyzjeRequest;

import java.io.File;
import java.io.IOException;

public class JsonToObject {

    public WydajDecyzjeRequest getJsontoWydajDecyzjeRequest(File json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WydajDecyzjeRequest WydajDec = objectMapper.readValue(json, WydajDecyzjeRequest.class);
        return WydajDec;
    }
    public UchylDecyzje getJsonToUchylDecyzyjne(File json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UchylDecyzje uchylDecyzje = objectMapper.readValue(json, UchylDecyzje.class);
        return uchylDecyzje;
    }
    public UchylDecyzje getJsonToRozliczDecyzje(File json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Roz uchylDecyzje = objectMapper.readValue(json, .class);
        return uchylDecyzje;
    }
//    public BlankietDto getJsonToBlankietDto(File json) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        BlankietDto blankietDto = objectMapper.readValue(json, BlankietDto.class);
//        return blankietDto;
//    }
}
