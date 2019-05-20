package com.springcloud.common.tool.util.objectcopier;

import com.springcloud.common.tool.exception.BizErrorCode;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.HashBasedTable;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 数据对象复制转换器
 * <p>
 * created by jiangyf on 2019-03-21 19:13
 */
public class ObjectCopier {

    private static volatile HashBasedTable<Class, Class, BeanCopier> shallowCopierCache = HashBasedTable.create();
    private static final Object shallowCacheLock = new Object();

    /**
     * 浅复制
     *
     * @param srcClass
     * @param targetClass
     * @return
     */
    private static BeanCopier getShallowCopier(Class srcClass, Class targetClass) {
        BeanCopier copier = shallowCopierCache.get(srcClass, targetClass);

        if (copier == null) {
            synchronized (shallowCacheLock) {
                copier = shallowCopierCache.get(srcClass, targetClass);

                if (copier == null) {
                    BeanCopier beanCopier = BeanCopier.create(srcClass, targetClass, false);
                    shallowCopierCache.put(srcClass, targetClass, beanCopier);
                    return beanCopier;
                } else {
                    return copier;
                }
            }
        }
        return copier;
    }

    /**
     * 浅复制，只复制类型相同，名字相同的属性
     * 效率高, 优先使用
     *
     * @param <E>
     * @return
     */
    public static <E> E copyObject(Class<E> targetClass, Object src) {
        if (src == null) {
            return null;
        }

        BeanCopier copy = getShallowCopier(src.getClass(), targetClass);
        try {
            Object o = targetClass.newInstance();
            copy.copy(src, o, null);
            return (E) o;
        } catch (Exception e) {
            throw BizErrorCode.DATA_CONVERT_ERROR.build();
        }
    }

    /**
     * 浅复制，只复制类型相同，名字相同的属性
     * 效率高, 优先使用
     *
     * @param <E>
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <E, R> E copyObject(Class<E> targetClass, R src, ObjectCopierFuture future) {
        E e = copyObject(targetClass, src);

        if (src == null) {
            return null;
        }

        future.process(src, e);
        return e;
    }

    /**
     * 浅复制，只复制类型相同，名字相同的属性,忽略名字相同，类别不同的属性
     * 效率高, 优先使用
     *
     * @param <E>
     * @return
     */
    public static <E> List<E> copyList(Class<E> targetClass, Collection<?> src) {
        List<E> result = new ArrayList<E>();
        if (CollectionUtils.isEmpty(src)) {
            return result;
        }
        try {
            for (Object o : src) {
                result.add(copyObject(targetClass, o));
            }
            return result;
        } catch (Exception e) {
            throw BizErrorCode.DATA_CONVERT_ERROR.build();
        }
    }

    /**
     * 浅复制，只复制类型相同，名字相同的属性,忽略名字相同，类别不同的属性
     * 效率高, 优先使用
     *
     * @param <E>
     * @return
     */
    public static <E, R> List<E> copyList(Class<E> targetClass, Collection<R> src, ObjectCopierFuture<E, R> future) {
        List<E> result = new ArrayList<>();
        try {
            for (Object o : src) {
                E e = copyObject(targetClass, o, future);
                result.add(e);
            }
            return result;
        } catch (Exception e) {
            throw BizErrorCode.DATA_CONVERT_ERROR.build();
        }
    }

    /**
     * 分页数据复制
     *
     * @param clazz
     * @param page
     * @param <E>
     * @param <R>
     * @return
     */
    public static <E, R> PageInfo<E> copierPage(Class<E> clazz, PageInfo<R> page) {
        List<E> record = copyList(clazz, page.getList());
        return convert(page, record);
    }

    /**
     * 分页数据复制
     *
     * @param clazz
     * @param page
     * @param future
     * @param <E>
     * @param <R>
     * @return
     */
    public static <E, R> PageInfo<E> copierPage(Class<E> clazz, PageInfo<R> page, ObjectCopierFuture<E, R> future) {
        List<E> record = copyList(clazz, page.getList(), future);
        return convert(page, record);
    }

    /**
     * 对象类型转换
     *
     * @param page
     * @param record
     * @param <E>
     * @param <R>
     * @return
     */
    public static <E, R> PageInfo<E> convert(PageInfo<R> page, List<E> record) {
        PageInfo<E> res = new PageInfo<E>();
        res.setPageNum(page.getPageNum());
        res.setPageSize(page.getPageSize());
        res.setTotal(page.getTotal());
        res.setPages(page.getPages());
        res.setHasNextPage(page.isHasNextPage());
        res.setList(record);
        return res;
    }
}
