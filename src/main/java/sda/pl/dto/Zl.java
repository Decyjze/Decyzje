package sda.pl.dto;

import java.util.HashMap;
import java.util.Map;

public class Zl {

    private String numer;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}