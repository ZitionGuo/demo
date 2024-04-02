package com.example.demo.chain;

import com.example.demo.dto.BatchCleanAuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guozixuan
 * @date 2024/3/2 11:04
 */
@Component
public class ToolChain {

    @Autowired
    private List<TodoHandler> handlers;

    public void executeHandlers(BatchCleanAuditDTO auditDTO) {
        for (TodoHandler handler : handlers) {
            if (!handler.handle(auditDTO)) {
                // 如果处理失败，记录日志并终止操作
                System.out.println("操作终止，记录日志...");
                break;
            }
        }
    }

}
