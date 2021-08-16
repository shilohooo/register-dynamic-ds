package org.shiloh.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author shiloh
 * @date 2021/8/16 15:22
 */
@Component
public class RegisterDynamicTransactionManagerFactory implements BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    public void registerTransactionManager(String beanName, DataSource dataSource) {
        beanFactory.registerSingleton(beanName, new DataSourceTransactionManager(dataSource));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
