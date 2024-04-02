package com.example.demo.chain;

import com.example.demo.dto.BatchCleanAuditDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author guozixuan
 * @date 2024/3/2 11:03
 */
@Component
@Order(1)
public class RecordHandler implements TodoHandler{
    @Override
    public boolean handle(BatchCleanAuditDTO auditDTO) {
        System.out.println("RecordHandler");
        return true;
    }
}
