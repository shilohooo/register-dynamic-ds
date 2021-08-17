package org.shiloh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 系统数据源配置实体，对应数据库表为：t_data_source_config
 *
 * @author shiloh
 * @date 2021/8/16 14:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataSourceConfig implements RowMapper<DataSourceConfig> {
    /**
     * ID，自增主键
     */
    private Long id;

    /**
     * 系统编码，唯一
     */
    private String systemCode;

    /**
     * 数据库驱动全限定类名，例如：com.mysql.cj.jdbc.Driver
     */
    private String driverClassName;

    /**
     * Jdbc数据库连接字符串
     */
    private String jdbcUrl;

    /**
     * 数据库连接帐号
     */
    private String username;

    /**
     * 数据库连接密码
     */
    private String password;

    /**
     * RowMapper映射重写实现：
     * <p>
     * 指定查询出的每一行数据如何映射为DataSourceConfig实体
     *
     * @param rs     查询结果集
     * @param rowNum 行号
     * @author shiloh
     * @date 2021/8/17 9:59
     */
    @Override
    public DataSourceConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DataSourceConfig.builder()
                .systemCode(rs.getString("system_code"))
                .driverClassName(rs.getString("driver_class_name"))
                .jdbcUrl(rs.getString("jdbc_url"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .build();
    }
}
