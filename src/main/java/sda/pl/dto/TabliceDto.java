package sda.pl.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class TabliceDto {
    @NonNull
    private String numer;
    @NonNull
    private ZnakLegalizacyjnyDto zl;
    @NonNull
    private List<BlankietDto> blankiety;

}