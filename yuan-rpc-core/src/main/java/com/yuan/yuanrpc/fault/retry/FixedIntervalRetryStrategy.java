package com.yuan.yuanrpc.fault.retry;

import com.github.rholder.retry.*;
import com.yuan.yuanrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 固定时间间隔 - 重试策略
 */
@Slf4j
public class FixedIntervalRetryStrategy implements RetryStrategy{

    /**
     * 重试
     * @param callable
     * @return
     * @throws Exception
     */
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws ExecutionException, RetryException {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                // 重试条件：发生 Exception 异常时
                .retryIfExceptionOfType(Exception.class)
                // 重试等待策略：固定时间间隔策略
                .withWaitStrategy(WaitStrategies.fixedWait(3L, TimeUnit.SECONDS))
                // 重试停止策略：超过最大重试次数停止重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                // 重试工作：监听重试，每次重试除了再次执行外，打印当前的重试次数
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数 {}", attempt.getAttemptNumber());
                    }
                })
                .build();

        return retryer.call(callable);
    }
}
