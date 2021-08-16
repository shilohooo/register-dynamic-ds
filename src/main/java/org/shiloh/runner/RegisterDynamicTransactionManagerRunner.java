package org.shiloh.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shiloh.dao.DataSourceConfigDao;
import org.shiloh.factory.RegisterDynamicTransactionManagerFactory;
import org.shiloh.util.SpringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author shiloh
 * @date 2021/8/16 15:21
 */
@Order(2)
@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterDynamicTransactionManagerRunner implements CommandLineRunner {
    private final DataSourceConfigDao dataSourceConfigDao;

    private final RegisterDynamicTransactionManagerFactory factory;

    private static final String BEAN_NAME_TEMPLATE = "%sTransactionManager";

    @Override
    public void run(String... args) throws Exception {
        final Map<String, DataSource> dataSourceMap = SpringUtils.getBeansOfType(DataSource.class);
        dataSourceConfigDao.findAll().forEach(dataSourceConfig -> {
            final String beanName = String.format(BEAN_NAME_TEMPLATE, dataSourceConfig.getSystemCode());
            factory.registerTransactionManager(beanName, dataSourceMap.get(dataSourceConfig.getSystemCode()));
        });
        dataSourceMap.clear();
        log.info("Inject dynamic DataSourceTransactionManager manager completed.");
    }
}
