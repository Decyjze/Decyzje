package sda.pl;

import sda.pl.connection.JsonToObject;
import sda.pl.dto.UchylDecyzje;
import sda.pl.dto.WydajDecyzjeRequest;
import sda.pl.service.WydanieDecyzjiService;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        JsonToObject jsonToObject = new JsonToObject();

        WydajDecyzjeRequest wydajDecyzjeRequest = jsonToObject.getJsontoWydajDecyzjeRequest(new File("json/wydajDec.json"));
        UchylDecyzje uchylDecyzje = jsonToObject.getJsonToUchylDecyzyjne(new File("json/UchylDecyzje.json"));

        WydanieDecyzjiService wydanieDecyzjiService = new WydanieDecyzjiService(wydajDecyzjeRequest,uchylDecyzje);





    }
}
