package sda.pl.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UchylDecyzje {
    private Long decyzjaId;
    private LocalDate dataUchylenia;
}
