package sda.pl.entity;

import java.time.LocalDateTime;
//Emil
public class DecyzjaEntity {
    private static Long staticId = 1L;
    private Long id;
    private String numer;
    private LocalDateTime dataWaznosci;
    private LocalDateTime dataWydania;

    public DecyzjaEntity(String numer, LocalDateTime dataWaznosci, LocalDateTime dataWydania) {
        this.id = staticId;
        staticId++;
        this.numer = numer;
        this.dataWaznosci = dataWaznosci;
        this.dataWydania = dataWydania;
    }
}

