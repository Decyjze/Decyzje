package sda.pl.repository;

import lombok.Getter;
import sda.pl.entity.BlankietEntity;
import sda.pl.entity.DanePodmiotu;

import java.util.List;

@Getter
public class Database {
    private List<BlankietEntity> dataBaseBlankiet;
    private List<DanePodmiotu> dataBaseDanePodmiotu;
    public boolean addDataBaseBlankiet(BlankietEntity blankietEntity) {
       return dataBaseBlankiet.add(blankietEntity);
    }
    public boolean adddataBaseDanePodmiotu(DanePodmiotu danePodmiotu) {
        return dataBaseDanePodmiotu.add(danePodmiotu);
    }


}
