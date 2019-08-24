package sda.pl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DanePodmiotu {

    private String imie;
    private String nazwisko;
    private String pesel;
    private String miasto;
    private String nazwaUlicy;
    private Integer nrDomu;

}