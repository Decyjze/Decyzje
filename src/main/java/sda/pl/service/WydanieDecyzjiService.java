package sda.pl.service;

import sda.pl.dto.DanePodmiotuDto;
import sda.pl.dto.TabliceDto;
import sda.pl.dto.UchylDecyzje;
import sda.pl.dto.WydajDecyzjeRequest;
import sda.pl.entity.*;

import java.util.Collection;

public class WydanieDecyzjiService {

    private final WydajDecyzjeRequest dto;
    private  final UchylDecyzje uchylDecyzje;
    private  final UchylDecyzje uchylDecyzje;
    private  final UchylDecyzje uchylDecyzje;
    private  final UchylDecyzje uchylDecyzje;
    private  final UchylDecyzje uchylDecyzje;
    private  final UchylDecyzje uchylDecyzje;
    private  final UchylDecyzje uchylDecyzje;
    private  final UchylDecyzje uchylDecyzje;

    public WydanieDecyzjiService(WydajDecyzjeRequest dto, UchylDecyzje uchylDecyzje) {
        this.dto = dto;
        this.uchylDecyzje = uchylDecyzje;
    }


    public Long wydajDecyzje(WydajDecyzjeRequest dto) {
        DecyzjaEntity decyzjaEntity = new DecyzjaEntity(dto.getNumer(), dto.getDataWaznosci(),
                dto.getDataWydania());

        blankietService(dto, decyzjaEntity);
        danePodmiotuService(dto, decyzjaEntity);

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

    public Long danePodmiotuService(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        DanePodmiotuDto danePodmiotuDto = dto.getDanePodmiotu();//dane podmiatu
        DanePodmiotu danePodmiotu = new DanePodmiotu(decyzjaEntity.getId(), danePodmiotuDto.getImie()
                , danePodmiotuDto.getNazwisko(), danePodmiotuDto.getPesel(), danePodmiotuDto.getMiasto(), danePodmiotuDto.getNazwaUlicy()
                , danePodmiotuDto.getNrDomu(), danePodmiotuDto.getNrDomu());

        PodmiotEntity podmiotEntity = new PodmiotEntity(danePodmiotu.getPodmiotId(), uchylDecyzje.getDecyzjaId(), danePodmiotu.getNrWariantu());

        return danePodmiotu.getPodmiotId();
    }

    public void blankietService(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        dto.getTablice().stream().map(TabliceDto::getBlankiety)
                .flatMap(Collection::stream)
                .forEach(blankietDto ->
                        new BlankietEntity(decyzjaEntity.getId(), blankietDto.getNumer(), getTyp(blankietDto.getTyp())));
    }

    private TypDokumentu getTyp(String typ) {
        if (typ.equals("DR")) {
            return TypDokumentu.DR;
        }
        return TypDokumentu.PC;
    }

    public Long uchylenieDecyzjiService(UchylDecyzje uchylDecyzje) {
        UchylenieDecyzjiEntity uchylenieDecyzjiEntity = new UchylenieDecyzjiEntity(uchylDecyzje.getDecyzjaId(),
                uchylDecyzje.getDataUchylenia());
        return uchylenieDecyzjiEntity.getId();
    }



}
