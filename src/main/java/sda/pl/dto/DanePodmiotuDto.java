package sda.pl.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class DanePodmiotuDto {
@NonNull
    private String imie;
    @NonNull
    private String nazwisko;
    @NonNull
    private String pesel;
    @NonNull
    private String miasto;
    @NonNull
    private String nazwaUlicy;
    @NonNull
    private Integer nrDomu;

}