package org.shiloh.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * JdbcTemplate注册工厂：
 * <p>
 * 主要负责使用传入的数据源对象注册一个{@link JdbcTemplate}对象的实例到容器中（单例）
 *
 * @author shiloh
 * @date 2021/8/16 15:56
 */
@Component
public class RegisterDynamicJdbcTemplateFactory implements BeanFactoryAware {
    /**
     * 默认的Bean工厂
     */
    private DefaultListableBeanFactory beanFactory;

    /**
     * 注册{@link JdbcTemplate}到容器中
     *
     * @param beanName   Bean名称
     * @param dataSource {@link DataSource}对象
     * @author shiloh
     * @date 2021/8/17 10:16
     */
    public void registerJdbcTemplate(String beanName, DataSource dataSource) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        beanFactory.registerSingleton(beanName, jdbcTemplate);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
