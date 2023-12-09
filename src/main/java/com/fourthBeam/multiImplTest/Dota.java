package com.fourthBeam.multiImplTest;

import org.springframework.stereotype.Service;

/**
 * @description：TODO
 * @author： liangping
 * @create： 2023/12/8 21:46
 */
@Service("Dota")
public class Dota implements Game{
    @Override
    public String getName() {
        return "Dota";
    }
}
