package sda.pl.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DanePodmiotuDto {

    private String imie;
    private String nazwisko;
    private String pesel;
    private String miasto;
    private String nazwaUlicy;
    private Integer nrDomu;

}