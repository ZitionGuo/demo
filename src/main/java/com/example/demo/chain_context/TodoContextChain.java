package com.example.demo.chain_context;

import com.example.demo.dto.BatchCleanAuditDTO;
import com.example.demo.dto.CommonContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guozixuan
 * @date 2024/3/2 12:09
 */
@Component
public class TodoContextChain {

    @Autowired
    private List<TodoContextHandler> handlers;

    public void executeHandlers(BatchCleanAuditDTO auditDTO, CommonContext commonContext) {
        for (TodoContextHandler handler : handlers) {
            if (!handler.handle(auditDTO, commonContext)) {
                System.out.println("操作终止，记录日志...");
                break;
            }
        }
    }
}
