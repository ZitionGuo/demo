package com.example.demo.chain;

import com.example.demo.dto.BatchCleanAuditDTO;

/**
 * @author guozixuan
 * @date 2024/3/2 10:59
 */
public interface TodoHandler {
    boolean handle(BatchCleanAuditDTO auditDTO);
}
