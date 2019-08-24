package sda.pl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TabliceDto {

    private String numer;
    private ZnakLegalizacyjnyDto zl;
    private List<BlankietDto> blankiety;

}