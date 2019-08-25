package sda.pl.service;

import sda.pl.dto.DanePodmiotuDto;
import sda.pl.dto.TabliceDto;
import sda.pl.dto.UchylDecyzje;
import sda.pl.dto.WydajDecyzjeRequest;
import sda.pl.entity.*;
import sda.pl.repository.Database;

import java.util.Collection;
import java.util.stream.Stream;

public class WydanieDecyzjiCreate {

    private final WydajDecyzjeRequest dto;
    private final UchylDecyzje uchylDecyzje;
    private final Database database;
    private DecyzjaEntity decyzjaEntity;
    private PodmiotEntity podmiotEntity;
    private DanePodmiotu danePodmiotu;

    public WydanieDecyzjiCreate(WydajDecyzjeRequest dto, UchylDecyzje uchylDecyzje, Database database) {
        this.dto = dto;
        this.uchylDecyzje = uchylDecyzje;
        this.database = database;
        wydajDecyzjeCreate(dto);
        uchylenieDecyzjiCreate(uchylDecyzje);
        blankietCreate(dto, decyzjaEntity);
        danePodmiotuCreate(dto, decyzjaEntity);
        podmiotEnityCreate();
    }

    private Long wydajDecyzjeCreate(WydajDecyzjeRequest dto) {
        decyzjaEntity = new DecyzjaEntity(dto.getNumer(), dto.getDataWaznosci(), dto.getDataWydania());
        database.adddataBaseDecyzjaEntity(decyzjaEntity);
        return decyzjaEntity.getId();
    }

    private void podmiotEnityCreate() {
       // boolean present = database.getDataBaseDanePodmiotu().stream().filter(s -> s.getPesel().equals(dto.getDanePodmiotu().getPesel())).findFirst().isPresent();
        boolean present = database.getDataBaseDanePodmiotu().stream().anyMatch(s -> s.getPesel().equals(dto.getDanePodmiotu().getPesel()));
        if (present){
            Long nrWariantu = database.getDataBaseDanePodmiotu().get(0).getNrWariantu();
            new PodmiotEntity(nrWariantu);
        }else {
           new PodmiotEntity(1L,uchylDecyzje.getDecyzjaId(),1L);
        }
    }

    private Long danePodmiotuCreate(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        DanePodmiotuDto danePodmiotuDto = dto.getDanePodmiotu();

        danePodmiotu = new DanePodmiotu(decyzjaEntity.getId(), podmiotEntity.getNrWariantu(), danePodmiotuDto.getImie()
                , danePodmiotuDto.getNazwisko(), danePodmiotuDto.getPesel(), danePodmiotuDto.getMiasto(), danePodmiotuDto.getNazwaUlicy()
                , danePodmiotuDto.getNrDomu(), danePodmiotuDto.getNrDomu());

        database.adddataBaseDanePodmiotu(danePodmiotu);
        database.addDataBasePodmiotEntity(new PodmiotEntity(danePodmiotu.getPodmiotId(), uchylDecyzje.getDecyzjaId(),
                danePodmiotu.getNrWariantu()));

        return danePodmiotu.getPodmiotId();
    }

    private void blankietCreate(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        dto.getTablice().stream().map(TabliceDto::getBlankiety)
                .flatMap(Collection::stream)
                .forEach(blankietDto ->
                        database.addDataBaseBlankiet(new BlankietEntity(decyzjaEntity.getId(), blankietDto.getNumer(),
                                getTyp(blankietDto.getTyp()))));
    }

    private TypDokumentu getTyp(String typ) {
        if (typ.equals("DR")) {
            return TypDokumentu.DR;
        }
        return TypDokumentu.PC;
    }

    private Long uchylenieDecyzjiCreate(UchylDecyzje uchylDecyzje) {
        UchylenieDecyzjiEntity uchylenieDecyzjiEntity = new UchylenieDecyzjiEntity(uchylDecyzje.getDecyzjaId(),
                uchylDecyzje.getDataUchylenia());
        database.addDataBaseUchylenieDecyzjiEntity(uchylenieDecyzjiEntity);
        return uchylenieDecyzjiEntity.getId();
    }
}
