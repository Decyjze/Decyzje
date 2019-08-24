package sda.pl.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlankietEntity  {
    private static Long staticId = 1L;
    private Long id;
    private Long decyzjaId;
    private String numer;
    private TypDokumentu typ;

    public BlankietEntity(Long decyzjaId, String numer, TypDokumentu typ) {
        this.id = staticId;
        staticId++;
        this.decyzjaId = decyzjaId;
        this.numer = numer;
        this.typ = typ;
    }
}
