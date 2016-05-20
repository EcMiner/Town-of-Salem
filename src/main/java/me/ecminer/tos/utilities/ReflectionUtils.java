package me.ecminer.tos.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;

public class ReflectionUtils {

    public static final String version;
    private static final String spigotPackage = "net.minecraft.server.";

    static {

        Pattern pattern = Pattern.compile("v\\d_\\d_R\\d");
        Matcher matcher = pattern.matcher(Bukkit.getServer().getClass()
                .getPackage().getName());
        if (matcher.find())
            version = matcher.group();
        else
            version = null;

    }

    public static Class<?> getNMSClass(String className) {
        try {
            return Class.forName(spigotPackage + version + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeStaticMethod(Class<?> cls, String methodName,
                                            Class<?>[] parameterTypes, Object[] parameters) {
        return invokeMethod(cls, null, methodName, parameterTypes, parameters);
    }

    public static Object invokeMethod(Object instance, String methodName,
                                      Class<?>[] parameterTypes, Object[] parameters) {
        return invokeMethod(instance.getClass(), instance, methodName,
                parameterTypes, parameters);
    }

    public static Object invokeMethod(Class<?> cls, Object instance,
                                      String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        try {

            Method method = cls.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(instance, parameters);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Object getStatic(Class<?> cls, String fieldName) {
        return get(cls, null, fieldName);
    }

    public static Object get(Object instance, String fieldName) {
        return get(instance.getClass(), instance, fieldName);
    }

    public static Object get(Class<?> cls, Object instance, String fieldName) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void setStatic(Class<?> cls, String fieldName, Object value) {
        set(cls, null, fieldName, value);
    }

    public static void set(Object instance, String fieldName, Object value) {
        set(instance.getClass(), instance, fieldName, value);
    }

    public static void set(Class<?> cls, Object instance, String fieldName,
                           Object value) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Object newInstance(Class<?> cls, Class<?>[] parameterTypes,
                                     Object[] paremeters) {
        try {
            Constructor<?> constructor = cls.getConstructor(parameterTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(paremeters);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
