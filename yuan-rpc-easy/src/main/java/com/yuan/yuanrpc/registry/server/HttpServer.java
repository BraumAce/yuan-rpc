package com.yuan.yuanrpc.registry.server;

/**
 * HTTP 服务器接口
 */
public interface HttpServer {
    /**
     * 启动服务器
     */
    void doStart(int port);
}
