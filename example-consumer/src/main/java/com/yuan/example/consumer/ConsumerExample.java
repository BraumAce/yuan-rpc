package com.yuan.example.consumer;

import com.yuan.yuanrpc.config.RpcConfig;
import com.yuan.yuanrpc.utils.ConfigUtils;

/**
 * 服务消费者示例
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
