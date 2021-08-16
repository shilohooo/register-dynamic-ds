package org.shiloh.dao;

import org.shiloh.entity.DataSourceConfig;

import java.util.List;

/**
 * @author shiloh
 * @date 2021/8/16 14:39
 */
public interface DataSourceConfigDao {
    /**
     * find all data source configs
     *
     * @return all data source configs in db
     * @author shiloh
     * @date 2021/8/16 14:43
     */
    List<DataSourceConfig> findAll();
}
