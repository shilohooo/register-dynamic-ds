package org.shiloh.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Spring工具类，方便获取Bean实例
 *
 * @author shiloh
 * @date 2021/8/16 15:03
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    /**
     * 应用上下文对象
     */
    private static ApplicationContext applicationContext;

    /**
     * 设置{@link ApplicationContext}
     *
     * @author shiloh
     * @date 2021/8/17 10:03
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * 根据指定的Class类型获取容器中对应的Bean实例
     *
     * @param requireType Class类型
     * @return 对应给定类型的Bean实例
     * @author shiloh
     * @date 2021/8/17 10:03
     */
    public static <T> T getBean(Class<T> requireType) {
        return applicationContext.getBean(requireType);
    }

    /**
     * 根据指定的Class类型获取容器中对应的所有Bean实例，返回一个Key为Bean名称，Value为Bean实例的Map
     *
     * @param requireType Class类型
     * @return Key为Bean名称，Value为对应给定Class类型的Bean实例的Map
     * @author shiloh
     * @date 2021/8/17 10:04
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> requireType) {
        return applicationContext.getBeansOfType(requireType);
    }
}
