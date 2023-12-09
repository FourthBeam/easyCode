package com.fourthBeam.multiImplTest;

import org.springframework.stereotype.Service;

/**
 * @description：TODO
 * @author： liangping
 * @create： 2023/12/8 21:38
 */
@Service("HongJing")
public class HongJing implements Game{
    @Override
    public String getName() {
        return "HongJing";
    }
}
