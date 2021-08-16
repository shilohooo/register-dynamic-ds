package org.shiloh.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shiloh.dao.DataSourceConfigDao;
import org.shiloh.factory.RegisterDynamicDataSourceFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author shiloh
 * @date 2021/8/16 14:49
 */
@Order(1)
@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterDynamicDataSourceRunner implements CommandLineRunner {
    private final DataSourceConfigDao dataSourceConfigDao;

    private final RegisterDynamicDataSourceFactory factory;

    @Override
    public void run(String... args) {
        factory.registerDynamicDataSource(dataSourceConfigDao.findAll());
        log.info("Inject dynamic DataSource completed.");
    }
}
