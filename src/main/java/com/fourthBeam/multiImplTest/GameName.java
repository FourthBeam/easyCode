package com.fourthBeam.multiImplTest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description：TODO
 * @author： liangping
 * @create： 2023/12/8 21:39
 */
@AllArgsConstructor
@Getter
public enum GameName {
    HONG_JING("HongJing"),
    MO_SHOU_SHI_JIE("MoShouShiJie"),
    DOTA("Dota");

    String name;
}
