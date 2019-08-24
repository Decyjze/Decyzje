package sda.pl.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tablouse {

    private String numer;
    private Zl zl;
    private List<Blankiet> blankiet = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public Zl getZl() {
        return zl;
    }

    public void setZl(Zl zl) {
        this.zl = zl;
    }

    public List<Blankiet> getBlankiet() {
        return blankiet;
    }

    public void setBlankiet(List<Blankiet> blankiet) {
        this.blankiet = blankiet;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}