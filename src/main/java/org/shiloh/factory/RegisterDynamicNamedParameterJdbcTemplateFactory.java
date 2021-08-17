package org.shiloh.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 具名参数JdbcTemplate注册工厂：
 * <p>
 * 主要负责使用传入的{@link JdbcTemplate}对象注册一个{@link NamedParameterJdbcTemplate}对象的实例到容器中（单例）
 *
 * @author shiloh
 * @date 2021/8/16 16:05
 */
@Component
public class RegisterDynamicNamedParameterJdbcTemplateFactory implements BeanFactoryAware {
    /**
     * 默认的Bean工厂
     */
    private DefaultListableBeanFactory beanFactory;

    /**
     * 注册{@link NamedParameterJdbcTemplate}到容器中
     *
     * @param beanName     Bean名称
     * @param jdbcTemplate {@link JdbcTemplate}对象
     * @author shiloh
     * @date 2021/8/17 10:17
     */
    public void registerNamedParameterJdbcTemplate(String beanName, JdbcTemplate jdbcTemplate) {
        final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        beanFactory.registerSingleton(beanName, namedParameterJdbcTemplate);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
