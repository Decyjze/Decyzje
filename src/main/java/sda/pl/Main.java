package sda.pl;

import sda.pl.connection.JsonToObject;
import sda.pl.dto.WydajDecDto;
import sda.pl.entity.DecyzjaEntity;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonToObject jsonToObject = new JsonToObject();
        WydajDecDto jsontoWydajDec = jsonToObject.getJsontoWydajDec(new File("json/wydajDec.json"));


        Long id;
        DecyzjaEntity decyzjaEntity = new DecyzjaEntity()



    }
}
