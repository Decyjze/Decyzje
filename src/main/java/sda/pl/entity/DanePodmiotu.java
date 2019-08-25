package sda.pl.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DanePodmiotu {
    private static Long staticWariantId = 1L;
    private Long podmiotId;
    private Long nrWariantu;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String miasto;
    private String nazwaUlicy;
    private Integer nrDomu;
    private Integer nrMieszkania;

    public DanePodmiotu(Long podmiotId, String imie, String nazwisko, String pesel,
                        String miasto, String nazwaUlicy, Integer nrDomu, Integer nrMieszkania) {
        this.podmiotId = podmiotId;
        this.nrWariantu = staticWariantId;
        staticWariantId++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.miasto = miasto;
        this.nazwaUlicy = nazwaUlicy;
        this.nrDomu = nrDomu;
        this.nrMieszkania = nrMieszkania;
    }

    public static void setStaticWariantId(Long staticWariantId) {
        DanePodmiotu.staticWariantId = staticWariantId;
    }

    public void setPodmiotId(Long podmiotId) {
        staticWariantId++;
        this.podmiotId = podmiotId;
    }

    public void setNrWariantu(Long nrWariantu) {
        staticWariantId++;
        this.nrWariantu = nrWariantu;
    }

    public void setImie(String imie) {
        staticWariantId++;
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        staticWariantId++;
        this.nazwisko = nazwisko;
    }

    public void setPesel(String pesel) {
        staticWariantId++;
        this.pesel = pesel;
    }

    public void setMiasto(String miasto) {
        staticWariantId++;
        this.miasto = miasto;
    }

    public void setNazwaUlicy(String nazwaUlicy) {
        staticWariantId++;
        this.nazwaUlicy = nazwaUlicy;
    }

    public void setNrDomu(Integer nrDomu) {
        staticWariantId++;
        this.nrDomu = nrDomu;
    }

    public void setNrMieszkania(Integer nrMieszkania) {
        staticWariantId++;
        this.nrMieszkania = nrMieszkania;
    }
}
