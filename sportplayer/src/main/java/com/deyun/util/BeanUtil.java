package com.deyun.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

public class BeanUtil extends org.springframework.beans.BeanUtils{

    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiateClass(clazz);
    }

    public static <T> T copy(Object source, Class<T> clazz) {
        BeanCopier copier = BeanCopier.create(source.getClass(), clazz, false);
        T to = newInstance(clazz);
        copier.copy(source, to, null);
        return to;
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !ObjectUtils.isEmpty(obj);
    }
}
