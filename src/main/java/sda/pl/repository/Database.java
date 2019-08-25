package sda.pl.repository;

import lombok.Getter;
import sda.pl.entity.BlankietEntity;

import java.util.List;

@Getter
public class Database {
    private List<BlankietEntity> dataBaseBlankiet;

    public boolean addDataBaseBlankiet(BlankietEntity blankietEntity) {
       return dataBaseBlankiet.add(blankietEntity);
    }

}
