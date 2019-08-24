package sda.pl.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OznaczenieEntity {
    private static Long staticId = 1L;
    private Long id;
    private Long decyzjaId;
    private String numer;
    private RodzajOznaczenia rodzajOznaczenia;
    private Long zl2TpId;
    private Stan stan;

    public OznaczenieEntity(Long decyzjaId, String numer, RodzajOznaczenia rodzajOznaczenia,
                            Long zl2TpId, Stan stan) {
        this.id = staticId;
        staticId ++;
        this.decyzjaId = decyzjaId;
        this.numer = numer;
        this.rodzajOznaczenia = rodzajOznaczenia;
        this.zl2TpId = zl2TpId;
        this.stan = stan;
    }



}
