package sda.pl.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UchylenieDecyzjiEntity {
    private static Long staticId = 1L;
    private Long id;
    private Long decyzjaId;
    private LocalDate dataUchylenia;

    public UchylenieDecyzjiEntity(Long decyzjaId, LocalDate dataUchylenia) {
        this.id = staticId;
        staticId++;
        this.decyzjaId = decyzjaId;
        this.dataUchylenia = dataUchylenia;
    }



}
