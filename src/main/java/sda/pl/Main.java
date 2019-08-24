package sda.pl;

import sda.pl.connection.JsonToObject;
import sda.pl.dto.WydajDecDto;
import sda.pl.entity.DecyzjaEntity;
import sda.pl.request.WydajDecyzjeRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws IOException {
        WydajDecyzjeRequest wydajDecyzjeRequest = new WydajDecyzjeRequest("json/wydajDec.json");



    }
}
