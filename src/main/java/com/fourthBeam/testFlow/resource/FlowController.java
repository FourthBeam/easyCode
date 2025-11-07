package com.fourthBeam.testFlow.resource;

import com.fourthBeam.testFlow.app.entity.AData;
import com.fourthBeam.testFlow.domain.api.FlowResult;
import com.fourthBeam.testFlow.domain.core.FlowExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flow")
public class FlowController {

    @Autowired
    private FlowExecutor executor;

    @PostMapping("/a/start")
    public FlowResult<?> startA(@RequestBody AData data) {
        data.setFlowCode("A");
        FlowResult<?> result = executor.execute("A", data);
        return result;
    }
}
