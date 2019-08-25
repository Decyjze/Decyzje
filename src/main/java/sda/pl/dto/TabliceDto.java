package sda.pl.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class TabliceDto {

    private String numer;
    private ZnakLegalizacyjnyDto zl;
    private List<BlankietDto> blankiety;

}