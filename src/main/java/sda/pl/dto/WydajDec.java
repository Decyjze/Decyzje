package sda.pl.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WydajDec {

    private String numer;
    private String dataWydania;
    private String dataWaznosci;
    private List<Tablouse> tablice = null;
    private DanePodmiotu danePodmiotu;

}