package sda.pl.service;

import sda.pl.dto.*;
import sda.pl.entity.*;
import sda.pl.repository.Database;

import java.util.*;

public class WydanieDecyzjiCreate {

    private final WydajDecyzjeRequest dto;
    private final UchylDecyzje uchylDecyzje;
    private final Database database;
    private DecyzjaEntity decyzjaEntity;
    private PodmiotEntity podmiotEntity;


    public WydanieDecyzjiCreate(WydajDecyzjeRequest dto, UchylDecyzje uchylDecyzje, Database database) {
        this.dto = dto;
        this.uchylDecyzje = uchylDecyzje;
        this.database = database;
        wydajDecyzjeCreate(dto);
        uchylenieDecyzjiCreate(uchylDecyzje);
        blankietCreate(dto, decyzjaEntity);
        podmiotEnityCreate();
        danePodmiotuCreate(dto, decyzjaEntity);
    }

    private Long wydajDecyzjeCreate(WydajDecyzjeRequest dto) {
//      TW01.01: DTO_->dataWaznosci = Data wydania decyzji + 1 rok
        dateCheck(dto);
//        TW01.04: Numer blankietu nie może sie powtarzać z numerem blankietu w bazie
        numerBlankietuCheck(dto);
//        TW01.02: Numer decyzji nie może się powtarzać z Numerem decyzji w bazie
        NumerDecyzjiCheck(dto);

        decyzjaEntity = new DecyzjaEntity(dto.getNumer(), dto.getDataWaznosci(), dto.getDataWydania());
        database.adddataBaseDecyzjaEntity(decyzjaEntity);
        return decyzjaEntity.getId();
    }

    private void NumerDecyzjiCheck(WydajDecyzjeRequest dto) {
        String numerDecyzji = dto.getNumer();
        List<DecyzjaEntity> dataBaseDecyzjaEntity = database.getDataBaseDecyzjaEntity();
        boolean numerDecyzjiIstniejeWBazie = dataBaseDecyzjaEntity.stream()
                .map(DecyzjaEntity::getNumer)
                .anyMatch(numerDecyzjiEntity -> numerDecyzjiEntity.equals(numerDecyzji));
        if (numerDecyzjiIstniejeWBazie) {
            throw new IllegalArgumentException("Dane się powtarzają w bazie.");
        }
        List<TabliceDto> tablice = dto.getTablice();
        for (TabliceDto tabliceDto : tablice) {
            String numerTablicy = tabliceDto.getNumer();
        }
    }

    private void numerBlankietuCheck(WydajDecyzjeRequest dto) {
        List<String> numery = new ArrayList<>();
        List<TabliceDto> tabliceList = dto.getTablice();
        tabliceList.forEach(tabliceDto -> {
            String numerTp = tabliceDto.getNumer();
            String zlNumer = tabliceDto.getZl().getNumer();
            numery.add(numerTp);
            numery.add(zlNumer);
            List<BlankietDto> blankiety = tabliceDto.getBlankiety();
            blankiety.stream().map(BlankietDto::getNumer).forEach(numery::add);
        });

        Set<String> powtarzalnosc = new HashSet<>(numery);
        if (numery.size() != powtarzalnosc.size()) {
            throw new IllegalArgumentException("Dane się powtarzają");
        }
    }

    private void dateCheck(WydajDecyzjeRequest dto) {
        boolean isEqual = dto.getDataWaznosci().isEqual(dto.getDataWydania().plusYears(1L));
        if (!isEqual) {
            throw new IllegalStateException("Podana data waznosci nie jest rowna +1 rok od wydania");
        }
    }

    private void podmiotEnityCreate() {
//        Tw01.05: Numery oznaczeń oraz blankietów nie mogą się powtórzyć w ramach jednego komumikatu
//        (nie możemy przesłać w pliku json np. dwa razy blankietu o tym samym numerze).
        // boolean present = database.getDataBaseDanePodmiotu().stream().filter(s -> s.getPesel().equals(dto.getDanePodmiotu().getPesel())).findFirst().isPresent();
        boolean present = database.getDataBaseDanePodmiotu().stream().anyMatch(s -> s.getPesel().equals(dto.getDanePodmiotu().getPesel()));
        if (present) {
            Long nrWariantu = database.getDataBaseDanePodmiotu().get(0).getNrWariantu();
             podmiotEntity = new PodmiotEntity(nrWariantu);
        } else {
            podmiotEntity = new PodmiotEntity(1L, uchylDecyzje.getDecyzjaId(), 1L);
        }
        database.addDataBasePodmiotEntity(podmiotEntity);
    }

    private Long danePodmiotuCreate(WydajDecyzjeRequest dto, DecyzjaEntity decyzjaEntity) {
        DanePodmiotuDto danePodmiotuDto = dto.getDanePodmiotu();

        Long aLong = database.getDataBasePodmiotEntity().stream().map(PodmiotEntity::getNrWariantu).findFirst().get();

        DanePodmiotu danePodmiotu = new DanePodmiotu(decyzjaEntity.getId(), aLong, danePodmiotuDto.getImie()
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

//    private void oznaczenieEntityCreate() {
//        String numerRodzajOznaczenia = dto.getTablice().stream().map(TabliceDto::getZl).findFirst().get().getNumer();
//
//        new OznaczenieEntity(uchylDecyzje.getDecyzjaId(),dto.getNumer(),
//                getRodzajOznaczenia(numerRodzajOznaczenia),1L,getStan())
//    }
    private RodzajOznaczenia getRodzajOznaczenia(String numer) {
        if (numer.equals("")) {
            return RodzajOznaczenia.ZNAK_LEGALIZACYJNY;
        }
        return RodzajOznaczenia.TABLICA_POJAZDU;
    }

    private Stan getStan(String stan) {
        if (stan.equals("")) {
            return Stan.ODEBRANY;
        }
        return Stan.UTRACONY;
    }
}
