package sda.pl.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DecyzjaEntity {
    private static Long staticId = 1L;
    private Long id;
    private String numer;
    private LocalDate dataWaznosci;
    private LocalDate dataWydania;

    public DecyzjaEntity(String numer, LocalDate dataWaznosci, LocalDate dataWydania) {
        this.id = staticId;
        staticId++;
        this.numer = numer;
        this.dataWaznosci = dataWaznosci;
        this.dataWydania = dataWydania;
    }
}

