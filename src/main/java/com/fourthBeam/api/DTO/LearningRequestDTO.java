package com.fourthBeam.api.DTO;

import com.fourthBeam.annotation.EnhanceResponseField;
import com.fourthBeam.annotation.processor.enmus.EnhanceType;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LearningRequestDTO implements Serializable {

    @EnhanceResponseField(enhanceType = EnhanceType.MASK)
    private String param1;

    @EnhanceResponseField(enhanceType = EnhanceType.REGEX, rules ={"万元","哈哈"})
    private String param2;

    private String param3;
}
