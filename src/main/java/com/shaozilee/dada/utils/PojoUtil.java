package com.shaozilee.dada.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by lee on 15-4-12.
 */
public class PojoUtil {
    private static Logger logger = LogManager.getLogger(PojoUtil.class);

    /**
     * 将ResultSet数据填充到HashMap对象
     * @param data
     * @return
     */
    public static Map getBean(ResultSet data){
        Map bean = null;
        try{
            ResultSetMetaData meta = data.getMetaData();
            int count = meta.getColumnCount();
            bean = new HashMap();
            for(int i=1;i<=count;i++){
                String col = meta.getColumnName(i);
                Object val = data.getObject(i);
                bean.put(col,val);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 根据Class类型把结果集中的数据填充Bean
     * @param clazz
     * @param data
     * @return
     */
    public static Object getBean(Class clazz,ResultSet data){
        Object bean = null;
        try{
            ResultSetMetaData meta = data.getMetaData();
            int count = meta.getColumnCount();
            bean = clazz.newInstance();
            for(int i=1;i<=count;i++){
                String col = meta.getColumnName(i);
                Field field = clazz.getDeclaredField(col);
                Class type = field.getType();
                String val = data.getString(col);
                if(val == null)continue;
                if("java.lang.String".equals(type.getName())){
                    field.set(bean,val);
                }else{
                    if("".equals(val)){
                        continue;
                    }else{
                        Method method = type.getDeclaredMethod("valueOf", new Class[]{String.class});
                        field.set(bean,method.invoke(null,val));
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 根据Class类型把Map中key对应的值填充到Bean
     * @param clazz
     * @param data
     * @return
     */
    public static Object getBean(Class clazz,Map data){
        Object bean = null;
        try{
            bean = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            int len = fields.length;
            for (int i=0;i<len;i++){
                Field field = fields[i];
                String name = field.getName();
                Class type = field.getType();
                Object value = data.get(name);
                if(value == null)continue;
                String val = value.toString();
                if("".equals(val))continue;
                if("java.lang.String".equals(type.getName())){
                    field.set(bean,val);
                }else{
                    Method method = type.getDeclaredMethod("valueOf", new Class[]{String.class});
                    field.set(bean,method.invoke(null,val));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将ParameterMap转换成HashMap
     * @param map
     * @return
     */
    public static Map castParameter(Map<String,String[]> map){
        Map params = new HashMap();
        Set<String> set = map.keySet();
        for(String n:set){
            params.put(n,map.get(n)[0]);
        }
        return params;
    }


    /**
     * 根据Bean属性对应表中的字段
     * @param clazz
     * @return
     */
    public static String[] getBeanProperties(Class clazz){
        StringBuffer prop = new StringBuffer();
        StringBuffer propv = new StringBuffer();
        Field[] fields = clazz.getDeclaredFields();
        int len = fields.length;
        for (int i=0;i<len;i++) {
            Field field = fields[i];
            String name = field.getName();
            prop.append(name);
            propv.append("?");
            if(i+1<len){
                prop.append(",");
                propv.append(",");
            }
        }
        return new String[]{prop.toString(),propv.toString()};
    }

    /**
     * 合并fromBean的属性到toBean中
     * @param fromBean
     * @param toBean
     * @return
     */
    public static Object mergeBean(Object fromBean,Object toBean){
        try {
            Class clazz = fromBean.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for(int i=0;i<fields.length;i++){
                Field field = fields[i];
                Object value = field.get(fromBean);
                if(value != null){
                    field.set(toBean, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return toBean;
    }

}
