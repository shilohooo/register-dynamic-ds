package org.shiloh.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author shiloh
 * @date 2021/8/16 15:03
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> requireType) {
        return applicationContext.getBean(requireType);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> requireType) {
        return applicationContext.getBeansOfType(requireType);
    }
}
