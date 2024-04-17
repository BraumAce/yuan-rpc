package com.yuan.yuanrpc.serializer;

import com.yuan.yuanrpc.spi.SpiLoader;

/**
 * 序列化器工厂 (用于获取序列化器对象)
 */
public class SerializerFactory {
    /**
     * 序列化映射器 (用于实现单例)
     */
//    private static final Map<String, Serializer> KEY_SERIALIZER_MAP =
//            new HashMap<String, Serializer>() {{
//        put(SerializerKeys.JDK, new JdkSerializer());
//        put(SerializerKeys.JSON, new JsonSerializer());
//        put(SerializerKeys.Kryo, new KryoSerializer());
//        put(SerializerKeys.Hessian, new HessianSerializer());
//    }};

    // 从 SPI 加载指定的序列化器对象
    static {
        SpiLoader.load(Serializer.class);
        //SpiLoader.loadAll();
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static Serializer getInstance(String key){
        //return KEY_SERIALIZER_MAP.getOrDefault(key, DEFAULT_SERIALIZER);
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
