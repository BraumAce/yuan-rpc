package com.yuan.yuanrpc.fault.tolerant;

import com.yuan.yuanrpc.spi.SpiLoader;

import java.util.Spliterator;

/**
 * 容错策略工厂
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    // 默认容错策略
    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
