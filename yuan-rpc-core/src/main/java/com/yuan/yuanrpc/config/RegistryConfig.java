package com.yuan.yuanrpc.config;

import com.yuan.yuanrpc.registry.RegistryKeys;
import lombok.Data;

/**
 * RPC 框架注册中心配置
 */
@Data
public class RegistryConfig {
    // 注册中心类别
    private String registry = RegistryKeys.ETCD;
    // 注册中心地址
    private String address = "http://localhost:2380";

    // 用户名
    private String name;

    // 密码
    private String password;

    // 超时时间 (毫秒)
    private Long timeout = 10000L;

}
