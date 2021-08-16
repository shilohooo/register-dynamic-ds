package org.shiloh.factory;

import com.zaxxer.hikari.HikariDataSource;
import org.shiloh.entity.DataSourceConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shiloh
 * @date 2021/8/16 14:56
 */
@Component
public class RegisterDynamicDataSourceFactory implements BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    public void registerDynamicDataSource(List<DataSourceConfig> dataSourceConfigs) {
        dataSourceConfigs.forEach(this::registerDynamicDataSource);
    }

    public void registerDynamicDataSource(DataSourceConfig dataSourceConfig) {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
        hikariDataSource.setJdbcUrl(dataSourceConfig.getJdbcUrl());
        hikariDataSource.setUsername(dataSourceConfig.getUsername());
        hikariDataSource.setPassword(dataSourceConfig.getPassword());
        beanFactory.registerSingleton(dataSourceConfig.getSystemCode(), hikariDataSource);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
