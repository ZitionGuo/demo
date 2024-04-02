package com.example.demo.chain;

import com.example.demo.dto.BatchCleanAuditDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author guozixuan
 * @date 2024/3/2 11:02
 */
@Component
@Order(2)
public class QueryHandler implements TodoHandler{
    @Override
    public boolean handle(BatchCleanAuditDTO auditDTO) {
        System.out.println("queryHandler");
        return false;
    }
}
