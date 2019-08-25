package sda.pl.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PodmiotEntity {
    private static Long staticWariantId = 1L;
    private Long podmiotId;
    private Long decyzjaId;
    private Long nrWariantu;

    public PodmiotEntity(Long decyzjaId) {
        //wariant=1
        //podmiotid auto
        staticWariantId++;
        this.decyzjaId = decyzjaId;
    }

    public PodmiotEntity(Long podmiotId, Long decyzjaId, Long nrWariantu) {
        this.podmiotId = podmiotId;
        this.decyzjaId = decyzjaId;
        this.nrWariantu = nrWariantu;
    }
}
