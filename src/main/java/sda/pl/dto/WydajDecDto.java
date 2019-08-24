package sda.pl.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WydajDecDto {

    private String numer;
    private String dataWydania;
    private String dataWaznosci;
    private List<TabliceDto> tabliceDto;
    private DanePodmiotuDto danePodmiotuDto;

}