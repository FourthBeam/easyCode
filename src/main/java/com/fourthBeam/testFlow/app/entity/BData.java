package com.fourthBeam.testFlow.app.entity;

import com.fourthBeam.testFlow.domain.api.FlowData;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BData extends FlowData {
    private String orderId;
    private String resultMessage;
}
