package sda.pl.service;

import sda.pl.dto.DanePodmiotuDto;
import sda.pl.dto.TabliceDto;
import sda.pl.dto.UchylDecyzje;
import sda.pl.dto.WydajDecyzjeRequest;
import sda.pl.entity.*;
import sda.pl.repository.Database;

import java.util.Collection;

public class WydanieDecyzjiCreate {

    private final WydajDecyzjeRequest dto;
    private final UchylDecyzje uchylDecyzje;
    private final Database database;


    public WydanieDecyzjiCreate(WydajDecyzjeRequest dto, UchylDecyzje uchylDecyzje, Database database) {
        this.dto = dto;
        this.uchylDecyzje = uchylDecyzje;
        this.database = database;
        wydajDecyzjeCreate(dto);
        uchylenieDecyzjiCreate(uchylDecyzje);
    }


    private Long wydajDecyzjeCreate(WydajDecyzjeRequest dto) {
        DecyzjaEntity decyzjaEntity = new DecyzjaEntity(dto.getNumer(), dto.getDataWaznosci(),
                dto.getDataWydania());

        database.adddataBaseDecyzjaEntity(decyzjaEntity);

        blankietCreate(dto, decyzjaEntity);
        danePodmiotuCreate(dto, decyzjaEntity);
        return decyzjaEntity.getId();
    }

    private Long danePodmiotuCreate(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        DanePodmiotuDto danePodmiotuDto = dto.getDanePodmiotu();
        DanePodmiotu danePodmiotu = new DanePodmiotu(decyzjaEntity.getId(), danePodmiotuDto.getImie()
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
