package com.org.pizza.util.customMappings;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MappingInitializer {
    private static final String ROOT_PACKAGE_NAME = "com.org.pizza";

    public static void initMappings(ModelMapper modelMapper) {
        String configureMappingsMethodName = CustomMapping.class
                .getDeclaredMethods()[0]
                .getName();

        getClassesWithCustomMappings()
                .forEach(klass -> invokeMethodFromClass(klass, configureMappingsMethodName, modelMapper));

    }

    private static List<Class<?>> getClassesWithCustomMappings() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AssignableTypeFilter(CustomMapping.class));

        Set<BeanDefinition> candidates = scanner.findCandidateComponents(MappingInitializer.ROOT_PACKAGE_NAME);

        return candidates
                .stream()
                .map(BeanDefinition::getBeanClassName)
                .map(MappingInitializer::getClassFromName)
                .filter(Objects::nonNull)
                .filter(CustomMapping.class::isAssignableFrom)
                .collect(Collectors.toList());
    }

    private static Class<?> getClassFromName(String className) {

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void invokeMethodFromClass(Class<?> klass, String methodName, Object... params) {
        try {
            Method method = klass.getDeclaredMethod(methodName, ModelMapper.class);
            Object obj = klass.newInstance();
            method.invoke(obj, params);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
