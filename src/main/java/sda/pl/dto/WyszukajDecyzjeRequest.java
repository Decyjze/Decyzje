package sda.pl.dto;

import lombok.Getter;
@Getter

public class WyszukajDecyzjeRequest {

    Long idDecyzji;

    String numerDecyzji;

    String numerOznaczenia;

    Long podmiotId;
}
