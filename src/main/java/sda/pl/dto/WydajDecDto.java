package sda.pl.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class WydajDecDto {

    private String numer;
    private LocalDateTime dataWydania;
    private LocalDateTime dataWaznosci;
    private List<TabliceDto> tablice;
    private DanePodmiotuDto danePodmiotu;

}