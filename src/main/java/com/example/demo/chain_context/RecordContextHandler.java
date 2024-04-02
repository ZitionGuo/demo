package com.example.demo.chain_context;

import com.example.demo.dto.BatchCleanAuditDTO;
import com.example.demo.dto.CommonContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author guozixuan
 * @date 2024/3/2 11:03
 */
@Component
@Order(1)
public class RecordContextHandler implements TodoContextHandler {
    @Override
    public boolean handle(BatchCleanAuditDTO auditDTO, CommonContext commonContext) {
        commonContext.setName("myName");
        System.out.println("RecordHandler");
        return true;
    }
}
