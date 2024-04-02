package com.example.demo.service;

import com.example.demo.chain.ToolChain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author guozixuan
 * @date 2024/1/25 18:31
 */
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{

    private final ToolChain toolChain;

    @Override
    public void execute() {
        System.out.println("right");
    }
}
