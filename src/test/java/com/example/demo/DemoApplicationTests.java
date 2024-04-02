package com.example.demo;

import com.example.demo.dto.CommonContext;
import com.example.demo.chain_context.TodoContextChain;
import com.example.demo.dto.BatchCleanAuditDTO;
import com.example.demo.chain.ToolChain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private ToolChain toolChain;
    @Autowired
    private TodoContextChain todoContextChain;

    @Test
    void contextLoads() {
        toolChain.executeHandlers(new BatchCleanAuditDTO());
    }

    @Test
    void test1() {
        todoContextChain.executeHandlers(new BatchCleanAuditDTO(), new CommonContext());
    }

}
