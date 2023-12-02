package com.fourthBeam.api.DTO;

import com.fourthBeam.annotation.EnhanceResponseField;
import com.fourthBeam.annotation.processor.enmus.EnhanceType;
import com.fourthBeam.annotation.processor.impl.ERMaskProcessor;
import com.fourthBeam.annotation.processor.impl.ERRegexProcessor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LearningRequestDTO implements Serializable {

    @EnhanceResponseField(enhanceType = EnhanceType.MASK, rules = {"PHONE_NUM"})
    private String param1;

    @EnhanceResponseField(enhanceType = EnhanceType.REGEX, rules ={"万元",""})
    private String param2;
}
