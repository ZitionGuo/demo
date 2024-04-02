package com.example.demo.chain_context;

import com.example.demo.dto.BatchCleanAuditDTO;
import com.example.demo.dto.CommonContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author guozixuan
 * @date 2024/3/2 11:02
 */
@Component
@Order(2)
public class QueryContextHandler implements TodoContextHandler {
    @Override
    public boolean handle(BatchCleanAuditDTO auditDTO, CommonContext context) {
        System.out.println("queryHandler");
        System.out.println(context.getName());
        return true;
    }
}
