package sda.pl.repository;

import lombok.Getter;
import sda.pl.entity.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Database {
    private List<BlankietEntity> dataBaseBlankiet = new ArrayList<>();
    private List<DanePodmiotu> dataBaseDanePodmiotu;
    private List<DecyzjaEntity> dataBaseDecyzjaEntity;
    private List<OznaczenieEntity> dataBaseOznaczenieEntity;
    private List<PodmiotEntity> dataBasePodmiotEntity;
    private List<UchylenieDecyzjiEntity>dataBaseUchylenieDecyzjiEntity;


    public boolean addDataBaseBlankiet(BlankietEntity blankietEntity) {
       return dataBaseBlankiet.add(blankietEntity);
    }
    public boolean adddataBaseDanePodmiotu(DanePodmiotu danePodmiotu) {
        return dataBaseDanePodmiotu.add(danePodmiotu);
    }
    public boolean adddataBaseDecyzjaEntity(DecyzjaEntity decyzjaEntity){
        return dataBaseDecyzjaEntity.add(decyzjaEntity);
    }
    public boolean addDataBaseOznaczenieEntity(OznaczenieEntity oznaczenieEntity){
        return dataBaseOznaczenieEntity.add(oznaczenieEntity);
    }
    public boolean addDataBasePodmiotEntity(PodmiotEntity podmiotEntity){
        return dataBasePodmiotEntity.add(podmiotEntity);
    }
    public boolean addDataBaseUchylenieDecyzjiEntity(UchylenieDecyzjiEntity uchylenieDecyzjiEntity){
        return  dataBaseUchylenieDecyzjiEntity.add(uchylenieDecyzjiEntity);

    }



}
