package org.shiloh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shiloh
 * @date 2021/8/16 14:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataSourceConfig implements RowMapper<DataSourceConfig> {
    private Long id;

    private String systemCode;

    private String driverClassName;

    private String jdbcUrl;

    private String username;

    private String password;

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
