package com.springcloud.common.tool.util.objectcopier;

/**
 * Object 转换完成后进行的操作
 * <p>
 * created by jiangyf on 2019-03-21 19:13
 */
public interface ObjectCopierFuture<E, R> {

    void process(R src, E target);

}
