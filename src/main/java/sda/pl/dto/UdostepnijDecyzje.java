package sda.pl.dto;

import lombok.Getter;
import sda.pl.entity.DanePodmiotu;
import sda.pl.entity.DecyzjaEntity;
import sda.pl.entity.UchylenieDecyzjiEntity;

@Getter

public class UdostepnijDecyzje {

    DecyzjaEntity decyzja;
    UchylenieDecyzjiEntity uchylenieDecyzji;
    TabliceDto tablice;
    DanePodmiotu danePodmiotu;
}
