package sda.pl.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TabliceDto {

    private String numer;
    private ZnakLegalizacyjnyDto zl;
    private List<BlankietDto> blankiety;

}