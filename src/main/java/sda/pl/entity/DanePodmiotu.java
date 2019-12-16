package sda.pl.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DanePodmiotu {

    private Long podmiotId;
    private Long nrWariantu;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String miasto;
    private String nazwaUlicy;
    private Integer nrDomu;
    private Integer nrMieszkania;

    public DanePodmiotu(Long podmiotId, Long nrWariantu, String imie, String nazwisko,
                        String pesel, String miasto, String nazwaUlicy, Integer nrDomu, Integer nrMieszkania) {
        this.podmiotId = podmiotId;
        this.nrWariantu = nrWariantu;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.miasto = miasto;
        this.nazwaUlicy = nazwaUlicy;
        this.nrDomu = nrDomu;
        this.nrMieszkania = nrMieszkania;
    }


}
