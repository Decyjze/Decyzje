package sda.pl.dto;

import java.util.HashMap;
import java.util.Map;

public class Blankiety {

    private String numer;
    private String typ;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}