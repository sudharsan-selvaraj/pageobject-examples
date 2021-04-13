package com.testninja.pageobjects.wrappers;

import com.testninja.pageobjects.wrappers.annotations.Page;
import com.testninja.pageobjects.wrappers.annotations.PageObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PageObjectFactory {

    public static void init(Object classObject,
                            List<Class<?>> constructorParams,
                            List<Object> constructorArguments){
        try {
            assignPageObject(classObject, constructorParams, constructorArguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void assignPageObject(Object classObject,
                                         List<Class<?>> constructorParams,
                                         List<Object> constructorArguments) throws Exception {
        List<Field> pageObjectFields = getPageObjectFields(classObject);

        for (Field f : pageObjectFields) {
            Class<?> pageObjectClass = f.getType();
            if (isValidPage(pageObjectClass)) {
                initializeFieldValue(pageObjectClass, f, classObject, constructorParams, constructorArguments);
            } else {
                throw new Exception("Field " + f.getName() + " in class " + f.getDeclaringClass() + "" +
                        " has invalid page object annotation for type " + pageObjectClass.getName());
            }
        }
    }

    private static void initializeFieldValue(Class<?> pageObjectTypeClass,
                                             Field field,
                                             Object classObject,
                                             List<Class<?>> constructorParams,
                                             List<Object> constructorArguments) throws Exception {
       try {
           Object newInstance =  pageObjectTypeClass
                    .getConstructor(constructorParams.toArray(new Class<?>[]{}))
                   .newInstance(constructorArguments.toArray(new Object[]{}));
           field.setAccessible(true);
           field.set(classObject, newInstance);
       } catch (Throwable t) {
           t.printStackTrace();
       }
    }

    private static List<Field> getPageObjectFields(Object classObject) {
        Class<?> objectClass = classObject.getClass();
        List<Field> pageObjectFields = new ArrayList<>();
        for (Field f : objectClass.getDeclaredFields()) {
            if (isPageObjectField(f)) {
                pageObjectFields.add(f);
            }
        }
        for (Field f : classObject.getClass().getSuperclass().getDeclaredFields()) {
            if (isPageObjectField(f)) {
                pageObjectFields.add(f);
            }
        }
        return pageObjectFields;
    }

    private static Boolean isValidPage(Class<?> clazz) {
        Annotation annotation = clazz.getAnnotation(Page.class);
        return annotation != null;
    }

    private static Boolean isPageObjectField(Field f) {
        Annotation annotation = f.getAnnotation(PageObject.class);
        return annotation != null;
    }
}