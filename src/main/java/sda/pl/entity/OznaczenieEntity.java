package sda.pl.entity;

public class OznaczenieEntity {
    private static Long staticId = 1L;

    public OznaczenieEntity(Long id, Long decyzjaId, String numer, RodzajOznaczenia rodzajOznaczenia,
                            Long zl2TpId, Stan stan) {
        this.id = staticId;
        staticId ++;
        this.decyzjaId = decyzjaId;
        this.numer = numer;
        this.rodzajOznaczenia = rodzajOznaczenia;
        this.zl2TpId = zl2TpId;
        this.stan = stan;
    }

    private Long id;
    private Long decyzjaId;
    private String numer;
    private RodzajOznaczenia rodzajOznaczenia;
    private Long zl2TpId;
    private Stan stan;

}
