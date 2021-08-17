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
 * 动态数据源注册工厂：
 * <p>
 * 主要负责将数据库中配置的数据源信息转换为{@link HikariDataSource}对象的实例并注入到容器中（单例）
 *
 * @author shiloh
 * @date 2021/8/16 14:56
 */
@Component
public class RegisterDynamicDataSourceFactory implements BeanFactoryAware {

    /**
     * 默认的Bean工厂
     */
    private DefaultListableBeanFactory beanFactory;

    /**
     * 注册数据源{@link HikariDataSource}到容器中
     *
     * @param dataSourceConfigs 数据源配置列表
     * @author shiloh
     * @date 2021/8/17 10:15
     */
    public void registerDynamicDataSource(List<DataSourceConfig> dataSourceConfigs) {
        dataSourceConfigs.forEach(this::registerDynamicDataSource);
    }

    /**
     * 注册数据源到容器中
     *
     * @param dataSourceConfig 数据源配置
     * @author shiloh
     * @date 2021/8/17 10:15
     */
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
