package org.shiloh.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shiloh.dao.DataSourceConfigDao;
import org.shiloh.factory.RegisterDynamicJdbcTemplateFactory;
import org.shiloh.util.SpringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 项目启动成功后通过工厂对象将{@link JdbcTemplate}对象注册到容器中
 *
 * @author shiloh
 * @date 2021/8/16 15:59
 */
@Order(3)
@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterDynamicJdbcTemplateRunner implements CommandLineRunner {
    private final RegisterDynamicJdbcTemplateFactory factory;

    private final DataSourceConfigDao dataSourceConfigDao;

    /**
     * {@link JdbcTemplate} Bean名称格式化模板
     */
    private static final String BEAN_NAME_TEMPLATE = "%sJdbcTemplate";

    @Override
    public void run(String... args) throws Exception {
        final Map<String, DataSource> dataSourceMap = SpringUtils.getBeansOfType(DataSource.class);
        dataSourceConfigDao.findAll().forEach(dataSourceConfig -> {
            final String beanName = String.format(BEAN_NAME_TEMPLATE, dataSourceConfig.getSystemCode());
            factory.registerJdbcTemplate(beanName, dataSourceMap.get(dataSourceConfig.getSystemCode()));
        });
        log.info("Inject dynamic JdbcTemplate completed.");
    }
}
