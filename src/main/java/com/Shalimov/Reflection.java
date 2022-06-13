package com.Shalimov;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflection {
    public static Object createObject(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor<?> constructor = clazz.getConstructor();
        return constructor.newInstance();
    }

    public static List<?> classWithOutPublicMethods(Class<?> clazz) {
        List<String> listOfMethods = new ArrayList<>();
        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            if (!Modifier.isPublic(method.getModifiers())) {
//                System.out.println(method);
                listOfMethods.add(method.getName());
            }
        }
        return listOfMethods;
    }

    public static List<?> invokeMethodsWithOutParameters(Object value) {
        List<String> listOfMethods = new ArrayList<>();
        Class<?> clazz = value.getClass();
        Method[] listMethods = clazz.getDeclaredMethods();
        for (Method method : listMethods) {
            if (method.getParameterCount() == 0) {
                listOfMethods.add(method.getName());
            }
        }
        return listOfMethods;
    }

    public static List<?> methodsWithSignatureContainsFinal(Object object) {
        List<String> listOfMethods = new ArrayList<>();
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.FINAL == method.getModifiers()) {
//                System.out.println(method);
                listOfMethods.add(method.getName());
            }
        }
        return listOfMethods;
    }

    public static List<?> returnAllSuperClassesAndInterfacesOfClass(Class<?> clazz) {
        List<String> ListWithSuperClassesAndInterfaces = new ArrayList<>();
        Class<?>[] interfaces = clazz.getInterfaces();
        ListWithSuperClassesAndInterfaces.add(Arrays.toString(interfaces));
        Class<?> superclass = clazz.getSuperclass();
        ListWithSuperClassesAndInterfaces.add(superclass.toString());
        return ListWithSuperClassesAndInterfaces;
    }

    public static void changePrivateParameters(Object object) throws ReflectiveOperationException {
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                Class<?> type = field.getType();
                field.setAccessible(true);
                if (int.class.equals(type)) {
                    field.set(object, (byte) 0);
                } else if (double.class.equals(type)) {
                    field.setDouble(object, 0);
                } else if (boolean.class.equals(type)) {
                    field.set(object, false);
                } else if (char.class.equals(type)) {
                    field.set(object, '\u0000');
                } else {
                    field.set(object, null);
                }
                field.setAccessible(false);
            }
        }
    }
}
