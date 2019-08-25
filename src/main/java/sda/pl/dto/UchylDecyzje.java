package sda.pl.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UchylDecyzje {
    private Long decyzjaId;
    private LocalDate dataUchylenia;
}
