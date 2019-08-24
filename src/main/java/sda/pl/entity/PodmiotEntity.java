package sda.pl.entity;

public class PodmiotEntity {
    private Long podmiotId;
    private Long decyzjaId;
    private Long nrWariantu;

    public PodmiotEntity(Long podmiotId, Long decyzjaId, Long nrWariantu) {
        this.podmiotId = podmiotId;
        this.decyzjaId = decyzjaId;
        this.nrWariantu = nrWariantu;
    }
}
