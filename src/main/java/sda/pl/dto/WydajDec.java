package sda.pl.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WydajDec {

    private String numer;
    private String dataWydania;
    private String dataWaznosci;
    private List<Tablouse> tablice = null;
    private DanePodmiotu danePodmiotu;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getDataWydania() {
        return dataWydania;
    }

    public void setDataWydania(String dataWydania) {
        this.dataWydania = dataWydania;
    }

    public String getDataWaznosci() {
        return dataWaznosci;
    }

    public void setDataWaznosci(String dataWaznosci) {
        this.dataWaznosci = dataWaznosci;
    }

    public List<Tablouse> getTablice() {
        return tablice;
    }

    public void setTablice(List<Tablouse> tablice) {
        this.tablice = tablice;
    }

    public DanePodmiotu getDanePodmiotu() {
        return danePodmiotu;
    }

    public void setDanePodmiotu(DanePodmiotu danePodmiotu) {
        this.danePodmiotu = danePodmiotu;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}