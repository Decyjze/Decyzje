package sda.pl.service;

import sda.pl.dto.*;
import sda.pl.entity.*;
import sda.pl.repository.Database;

import java.util.*;

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
        boolean isEqual = dto.getDataWaznosci().isEqual(dto.getDataWydania().plusYears(1L));
        if (!isEqual) {
            throw new IllegalStateException("Podana data waznosci nie jest rowna +1 rok od wydania");
        }
        List<String> numery= new ArrayList<>();
        List<TabliceDto> tabliceList = dto.getTablice();
        for (TabliceDto tabliceDto : tabliceList) {
            String numerTp = tabliceDto.getNumer();
            String zlNumer = tabliceDto.getZl().getNumer();
            numery.add(numerTp);
            numery.add(zlNumer);
            List<BlankietDto> blankiety = tabliceDto.getBlankiety();
            for (BlankietDto blankietDto:blankiety){
                numery.add(blankietDto.getNumer());
            }


        }
        Set<String> powtarzalnosc = new HashSet<>(numery);
        if (numery.size()!=powtarzalnosc.size()){
            throw new IllegalArgumentException("Dane się powtarzają");
        }
        String numerDecyzji = dto.getNumer();
        Database database= new Database();
        List<BlankietEntity>

        Set<String> numeryDecyzji = new HashSet<>(BlankietEntity);
        if (numerDecyzji!=){
            throw new IllegalArgumentException("Dane się powtarzają w bazie.");
        }



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
        database.adddataBaseDanePodmiotu(danePodmiotu);

        PodmiotEntity podmiotEntity = new PodmiotEntity(danePodmiotu.getPodmiotId(), uchylDecyzje.getDecyzjaId(), danePodmiotu.getNrWariantu());
        return danePodmiotu.getPodmiotId();
    }

    public void blankietCreate(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        dto.getTablice()
                .stream().map(TabliceDto::getBlankiety)
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
