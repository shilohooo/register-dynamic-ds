package org.shiloh.dao.impl;

import lombok.RequiredArgsConstructor;
import org.shiloh.dao.DataSourceConfigDao;
import org.shiloh.entity.DataSourceConfig;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shiloh
 * @date 2021/8/16 14:44
 */
@Repository
@RequiredArgsConstructor
public class DataSourceConfigDaoImpl implements DataSourceConfigDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<DataSourceConfig> findAll() {
        final String sql = "select * from t_data_source_config";
        return namedParameterJdbcTemplate.query(sql, new DataSourceConfig());
    }

}
