package com.yuan.yuanrpc.registry.serializer;

import java.io.*;

/**
 * JDK 序列化器
 */
public class JdkSerializer implements Serializer{
    /**
     * 序列化：将 Java 对象转换为可输出的字节数组
     * @param object
     * @return
     * @param <T>
     * @throws IOException
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 反序列化：将字节数组转换为 Java 对象
     * @param bytes
     * @param type
     * @return
     * @param <T>
     * @throws IOException
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException{
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        } finally {
            objectInputStream.close();
        }
    }
}
