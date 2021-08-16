package org.shiloh.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shiloh.dao.DataSourceConfigDao;
import org.shiloh.factory.RegisterDynamicNamedParameterJdbcTemplateFactory;
import org.shiloh.util.SpringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author shiloh
 * @date 2021/8/16 16:07
 */
@Order(4)
@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterDynamicNamedParameterJdbcTemplateRunner implements CommandLineRunner {
    private final DataSourceConfigDao dataSourceConfigDao;

    private final RegisterDynamicNamedParameterJdbcTemplateFactory factory;

    private static final String BEAN_NAME_TEMPLATE = "%sNamedParameterJdbcTemplate";

    private static final String JDBC_TEMPLATE_BEAN_NAME_TEMPLATE = "%sJdbcTemplate";


    @Override
    public void run(String... args) throws Exception {
        final Map<String, JdbcTemplate> jdbcTemplateMap = SpringUtils.getBeansOfType(JdbcTemplate.class);
        dataSourceConfigDao.findAll().forEach(dataSourceConfig -> {
            final String beanName = String.format(BEAN_NAME_TEMPLATE, dataSourceConfig.getSystemCode());
            final String jdbcTemplateBeanName = String.format(JDBC_TEMPLATE_BEAN_NAME_TEMPLATE,
                    dataSourceConfig.getSystemCode());
            factory.registerNamedParameterJdbcTemplate(beanName, jdbcTemplateMap.get(jdbcTemplateBeanName));
        });
        log.info("Inject dynamic NamedParameterJdbcTemplate completed.");
        printRegisteredNamedParameterJdbcTemplateInfo();
    }

    private void printRegisteredNamedParameterJdbcTemplateInfo() {
        final Map<String, NamedParameterJdbcTemplate> namedParameterJdbcTemplateMap = SpringUtils.getBeansOfType(
                NamedParameterJdbcTemplate.class
        );
        namedParameterJdbcTemplateMap.forEach((beanName, namedParameterJdbcTemplate) -> {
            log.info("NamedParameterJdbcTemplate bean name: {}", beanName);
            log.info("NamedParameterJdbcTemplate bean info: {}", namedParameterJdbcTemplate);
            log.info("NamedParameterJdbcTemplate.getJdbcTemplate() bean name: {}",
                    namedParameterJdbcTemplate.getJdbcTemplate());

        });
    }
}
