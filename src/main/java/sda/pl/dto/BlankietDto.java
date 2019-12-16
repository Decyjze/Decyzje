package sda.pl.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class BlankietDto {
@NonNull
    private String numer;
@NonNull
    private String typ;

}