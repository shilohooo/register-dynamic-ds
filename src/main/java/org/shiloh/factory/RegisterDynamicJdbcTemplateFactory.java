package org.shiloh.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author shiloh
 * @date 2021/8/16 15:56
 */
@Component
public class RegisterDynamicJdbcTemplateFactory implements BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    public void registerJdbcTemplate(String beanName, DataSource dataSource) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        beanFactory.registerSingleton(beanName, jdbcTemplate);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
