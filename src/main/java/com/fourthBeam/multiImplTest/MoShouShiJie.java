package com.fourthBeam.multiImplTest;

import org.springframework.stereotype.Service;

/**
 * @description：TODO
 * @author： liangping
 * @create： 2023/12/8 21:47
 */
@Service("MoShouShiJie")
public class MoShouShiJie implements Game{
    @Override
    public String getName() {
        return "MoShouShiJie";
    }
}
