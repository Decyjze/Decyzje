package sda.pl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Tablice {

    private String numer;
    private Zl zl;
    private List<Blankiet> blankiet;

}