package sda.pl;

import sda.pl.connection.JsonToObject;
import sda.pl.dto.UchylDecyzje;
import sda.pl.dto.WydajDecyzjeRequest;
import sda.pl.entity.BlankietEntity;
import sda.pl.entity.DecyzjaEntity;
import sda.pl.repository.Database;
import sda.pl.service.WydanieDecyzjiCreate;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        JsonToObject jsonToObject = new JsonToObject();
        Database database = new Database();

        WydajDecyzjeRequest wydajDecyzjeRequest = jsonToObject.getJsontoWydajDecyzjeRequest(new File("json/wydajDec.json"));
        //System.out.println(wydajDecyzjeRequest);
        UchylDecyzje uchylDecyzje = jsonToObject.getJsonToUchylDecyzyjne(new File("json/UchylDecyzje.json"));
        new WydanieDecyzjiCreate(wydajDecyzjeRequest, uchylDecyzje, database);

        database.getDataBaseDecyzjaEntity().stream().map(DecyzjaEntity::getDataWaznosci).forEach(System.out::println);





    }
}
