package org.shiloh.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author shiloh
 * @date 2021/8/16 16:05
 */
@Component
public class RegisterDynamicNamedParameterJdbcTemplateFactory implements BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    public void registerNamedParameterJdbcTemplate(String beanName, JdbcTemplate jdbcTemplate) {
        final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        beanFactory.registerSingleton(beanName, namedParameterJdbcTemplate);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
