package sda.pl.request;

import sda.pl.connection.JsonToObject;
import sda.pl.dto.WydajDecDto;
import sda.pl.entity.DecyzjaEntity;
import java.io.File;
import java.io.IOException;


public class WydajDecyzjeRequest {
    private DecyzjaEntity decyzjaEntity;

    public WydajDecyzjeRequest(String path) throws IOException {
        JsonToObject jsonToObject = new JsonToObject();
        WydajDecDto jsontoWydajDec = jsonToObject.getJsontoWydajDec(new File(path));
        decyzjaEntity = new DecyzjaEntity(jsontoWydajDec.getNumer(),jsontoWydajDec.getDataWaznosci(),
                jsontoWydajDec.getDataWydania());
    }

    public DecyzjaEntity mapWydajDecyzje(){
        return decyzjaEntity;
    }

}
