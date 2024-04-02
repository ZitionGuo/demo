package com.example.demo.chain_context;

import com.example.demo.dto.BatchCleanAuditDTO;
import com.example.demo.dto.CommonContext;

/**
 * @author guozixuan
 * @date 2024/3/2 10:59
 */
public interface TodoContextHandler {
    boolean handle(BatchCleanAuditDTO auditDTO, CommonContext commonContext);
}
