package com.fourthBeam.testFlow.app.entity;

import com.fourthBeam.testFlow.domain.api.FlowData;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AData extends FlowData {
    private String userId;
    private int amount;
    private boolean validated;
}
