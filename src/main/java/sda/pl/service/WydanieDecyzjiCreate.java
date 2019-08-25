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
        List<DecyzjaEntity> dataBaseDecyzjaEntity = database.getDataBaseDecyzjaEntity();
        boolean numerDecyzjiIstniejeWBazie = dataBaseDecyzjaEntity.stream()
                .map(DecyzjaEntity::getNumer)
                .filter(numerDecyzjiEntity -> numerDecyzjiEntity.equals(numerDecyzji))
                .findFirst()
                .isPresent();
        if (numerDecyzjiIstniejeWBazie){
            throw new IllegalArgumentException("Dane się powtarzają w bazie.");
        }
        List<TabliceDto> tablice = dto.getTablice();
        for (TabliceDto tabliceDto : tablice) {
            String numerTablicy = tabliceDto.getNumer();
            String numeryZL = tabliceDto.getZl().getNumer();
            List<OznaczenieEntity> dataBaseOznaczenieEntity = database.getDataBaseOznaczenieEntity();
            boolean istniejeNumerTablicyWBazie = dataBaseOznaczenieEntity
                    .stream()
                    .map(OznaczenieEntity::getNumer)
                    .filter(numerOznaczenia -> numerOznaczenia.equals(numerTablicy))
                    .findFirst()
                    .isPresent();
            boolean istniejeNumerZLWBazie = dataBaseOznaczenieEntity
                    .stream()
                    .map(OznaczenieEntity::getNumer)
                    .filter(numerOznaczenia -> numerOznaczenia.equals(numeryZL))
                    .findFirst()
                    .isPresent();
            if (istniejeNumerTablicyWBazie||istniejeNumerZLWBazie){
                throw new IllegalArgumentException("Dane w bazie się nie powtarzają ");
            }

        }
        List<TabliceDto> tabliceDtos = dto.getTablice();
        for (TabliceDto tabliceDto : tabliceDtos) {
            List<BlankietDto> blankiety = tabliceDto.getBlankiety();
            for (BlankietDto blankietDto : blankiety) {
                String numerBlankietu = blankietDto.getNumer();
                List<BlankietEntity> blankietEntity = database.getDataBaseBlankiet();
                boolean numerBlankietuIstniejeWBazie = blankietEntity.stream()
                        .map(BlankietEntity::getNumer)
                        .filter(numerBlankietuEntity -> numerBlankietuEntity.equals(numerBlankietu))
                        .findFirst()
                        .isPresent();
                if (numerBlankietuIstniejeWBazie) {
                    throw new IllegalArgumentException("Dane się powtarzają w bazie.");
                }
            }
        }

        //znalezc z bazy z DanePodmiotu podmiotId po PESEL z DTO
        //nastepnie przeszukac baze z DecyzjaEntity i znalezc decyze po znalezionym podmiotId
        //jesli nie znalalo to warunek OK, w przeciwnym wypadku musisz sprawdzic czy
        //data terazniejsza jest pomiedzy data wydania a datta waznosci
        //jesli jest, to warunek NOT OK, jesli nie jest warunek OK





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
