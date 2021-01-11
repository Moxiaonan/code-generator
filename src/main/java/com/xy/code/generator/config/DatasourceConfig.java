package com.xy.code.generator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据库配置
 *
 * @author moxiaonan
 * @since 2020/12/28
 */
@ConfigurationProperties(prefix = "db")
public class DatasourceConfig {
    private String url;
    private String driverName;
    private String userName;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
