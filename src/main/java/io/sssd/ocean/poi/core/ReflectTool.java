package io.sssd.ocean.poi.core;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * *****************************************************
 * Copyright HSLD Corporation. All Rights Reserved
 * Description:反射工具类
 * CreateTime:2017年1月5日 下午1:40:06
 * CreateUser: 阿宾
 * ******************************************************
 */
public class ReflectTool {
    /*
     ***************************************
     * Description:设置对象属性值：obj 实体对象,fieldName 属性名,value 属性值
     * CreateTime :2017年1月5日 下午1:40:39
     * CreateUser :阿宾
     *****************************************
     */
    public static void setProperty(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            if (field != null) {
                Class<?> fieldType = field.getType();
                field.setAccessible(true);
                // 根据字段类型给字段赋值
                if (String.class == fieldType) {
                    String s1 = value.toString();
                    field.set(obj, s1);
                } else if ((Integer.TYPE == fieldType)
                        || (Integer.class == fieldType)) {
                    field.set(obj, Integer.parseInt(value.toString()));
                } else if ((Long.TYPE == fieldType)
                        || (Long.class == fieldType)) {
                    field.set(obj, Long.valueOf(value.toString()));
                } else if ((Float.TYPE == fieldType)
                        || (Float.class == fieldType)) {
                    field.set(obj, Float.valueOf(value.toString()));
                } else if ((BigDecimal.class == fieldType)) {
                    field.set(obj, new BigDecimal(value.toString()));
                } else if ((Short.TYPE == fieldType)
                        || (Short.class == fieldType)) {
                    field.set(obj, Short.valueOf(value.toString()));
                } else if ((Double.TYPE == fieldType)
                        || (Double.class == fieldType)) {
                    field.set(obj, Double.valueOf(value.toString()));
                } else if (Character.TYPE == fieldType) {
                    if ((value != null) && (value.toString().length() > 0)) {
                        field.set(obj,
                                Character.valueOf(value.toString().charAt(0)));
                    }
                } else if (Date.class == fieldType) {
                    if (value instanceof Date) {
                        field.set(obj, value);
                    } else if (value instanceof String) {
                        Date date;
                        if (((String) value).length() > 10) {
                            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.toString());
                        } else {

                            date = new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
                        }
                        field.set(obj, date);
                    }
                } else {
                    field.set(obj, value);
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
