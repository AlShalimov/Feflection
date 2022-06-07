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

    public static void classWithoutPublicMethods(Class<?> clazz) {
        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            if (!Modifier.isPublic(method.getModifiers())) {
                System.out.println(method);
            }
        }
    }

    public static List<?> invokeMethodsWithoutParameters(Object value) {
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


    public static void methodsWithSignatureContainsFinal(Object object) {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.FINAL == method.getModifiers()) {
                System.out.println(method);
            }
        }
    }

    public static void returnAllSuperClassesAndInterfacesOfClass(Class<?> clazz) {
        System.out.println("Superclass is : " + clazz.getSuperclass() + ".");
        System.out.println("Implement Interface : " + Arrays.toString(clazz.getInterfaces()));
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
