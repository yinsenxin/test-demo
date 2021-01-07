package com.mybatis.demo;

import com.ygjt.cargo.common.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

public class testObject {
    public static void main(String[] args) throws Exception {

        Users users = new Users("1", null, null);

        objCheckIsNull(users);

    }


    public static void objCheckIsNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("对象为空");
        }
        // 得到类对象
        Class clazz = object.getClass();
        // 得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(object);
                if (fieldValue == null || (fieldValue instanceof String && StringUtils.isEmpty((String) fieldValue))) {
                    if (field.isAnnotationPresent(NotEmpty.class)) {
                        NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
                        //获取 NotEmpty 这个代理实例所持有的 InvocationHandler
                        InvocationHandler h = Proxy.getInvocationHandler(notEmpty);
                        // 获取 AnnotationInvocationHandler 的 memberValues
                        Field fieldName = h.getClass().getDeclaredField("memberValues");
                        // 设置访问权限
                        fieldName.setAccessible(true);
                        // 获取 NotEmpty注解的属性
                        Map map = (Map) fieldName.get(h);
                        String message = (String) map.get("message");
                        throw new IllegalArgumentException(message);
                    }else if (field.isAnnotationPresent(NotNull.class)){
                        NotNull notNull = field.getAnnotation(NotNull.class);
                        InvocationHandler h = Proxy.getInvocationHandler(notNull);
                        // 获取 AnnotationInvocationHandler 的 memberValues
                        Field fieldName = h.getClass().getDeclaredField("memberValues");
                        // 设置访问权限
                        fieldName.setAccessible(true);
                        // 获取 NotNull
                        Map map = (Map) fieldName.get(h);
                        String message = (String) map.get("message");
                        throw new ServiceException("发送到港区联动 " + message);
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

}


class Users {

    @NotEmpty(message = "名称不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空1")
    private String password;
    @NotNull(message = "de不能为空")
    private Integer de;

    public Integer getDe() {
        return de;
    }

    public void setDe(Integer de) {
        this.de = de;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Users() {
    }

    public Users(@NotEmpty(message = "名称不能为空") String userName, @NotEmpty(message = "密码不能为空1") String password, @NotNull(message = "de不能为空") Integer de) {
        this.userName = userName;
        this.password = password;
        this.de = de;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
