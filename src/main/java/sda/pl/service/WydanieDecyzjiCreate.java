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
    }


    public Long wydajDecyzjeCreate(WydajDecyzjeRequest dto) {
        DecyzjaEntity decyzjaEntity = new DecyzjaEntity(dto.getNumer(), dto.getDataWaznosci(),
                dto.getDataWydania());

        blankietCreate(dto, decyzjaEntity);
        danePodmiotuCreate(dto, decyzjaEntity);

//        List<TabliceDto> tablice = dto.getTablice();
//        for (TabliceDto tabliceDto : tablice) {
//            List<BlankietDto> blankiety = tabliceDto.getBlankiety();
//            for (BlankietDto blankietDto : blankiety) {
//                new BlankietEntity(decyzjaEntity.getId(),blankietDto.getNumer()
//                        ,getTyp(blankietDto.getTyp()));
//            }
//        }
        return decyzjaEntity.getId();
    }

    public Long danePodmiotuCreate(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        DanePodmiotuDto danePodmiotuDto = dto.getDanePodmiotu();//dane podmiatu
        DanePodmiotu danePodmiotu = new DanePodmiotu(decyzjaEntity.getId(), danePodmiotuDto.getImie()
                , danePodmiotuDto.getNazwisko(), danePodmiotuDto.getPesel(), danePodmiotuDto.getMiasto(), danePodmiotuDto.getNazwaUlicy()
                , danePodmiotuDto.getNrDomu(), danePodmiotuDto.getNrDomu());

        PodmiotEntity podmiotEntity = new PodmiotEntity(danePodmiotu.getPodmiotId(), uchylDecyzje.getDecyzjaId(), danePodmiotu.getNrWariantu());
        return danePodmiotu.getPodmiotId();
    }

    public void blankietCreate(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        dto.getTablice().stream().map(TabliceDto::getBlankiety)
                .flatMap(Collection::stream)
                .forEach(blankietDto ->
                        database.addDataBaseBlankiet(new BlankietEntity(decyzjaEntity.getId(), blankietDto.getNumer(), getTyp(blankietDto.getTyp()))));
    }

    private TypDokumentu getTyp(String typ) {
        if (typ.equals("DR")) {
            return TypDokumentu.DR;
        }
        return TypDokumentu.PC;
    }

    public Long uchylenieDecyzjiCreate(UchylDecyzje uchylDecyzje) {
        UchylenieDecyzjiEntity uchylenieDecyzjiEntity = new UchylenieDecyzjiEntity(uchylDecyzje.getDecyzjaId(),
                uchylDecyzje.getDataUchylenia());
        return uchylenieDecyzjiEntity.getId();
    }


}
