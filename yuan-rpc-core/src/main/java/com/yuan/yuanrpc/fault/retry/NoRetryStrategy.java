package com.yuan.yuanrpc.fault.retry;

import com.yuan.yuanrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * 不重试 - 重试策略
 */
public class NoRetryStrategy implements RetryStrategy{

    /**
     * 重试
     * @param callable
     * @return
     * @throws Exception
     */
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
