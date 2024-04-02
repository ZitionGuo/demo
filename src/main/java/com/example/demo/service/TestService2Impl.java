package com.example.demo.service;

import com.example.demo.chain.ToolChain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author guozixuan
 * @date 2024/3/2 22:30
 */
@Service
@RequiredArgsConstructor
public class TestService2Impl implements TestService2 {

    private final ToolChain toolChain;

    @Override
    public void execute() {

    }
}
