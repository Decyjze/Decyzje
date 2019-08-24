package sda.pl.entity;

public class DanePodmiotu {
    private Long id;
    private Long podmiotId;
    private Long nrWariantu;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String miasto;
    private String nazwaUlicy;
    private Integer nrDomu;
    private Integer nrMieszkania;

    public DanePodmiotu(Long id, Long podmiotId, Long nrWariantu, String imie, String nazwisko, String pesel,
                        String miasto, String nazwaUlicy, Integer nrDomu, Integer nrMieszkania) {
        this.id = id;
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
