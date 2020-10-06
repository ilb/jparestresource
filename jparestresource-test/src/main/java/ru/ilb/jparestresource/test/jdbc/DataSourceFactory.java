/*
 * Copyright 2020 slavb.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.ilb.jparestresource.test.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;

/**
 *
 * @author slavb
 */
public class DataSourceFactory {

    private HikariConfig config;

    private HikariConfig getConfig() {
        if (config == null) {
            Properties props = loadProperties("application.properties");
            config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("spring.datasource.url"));
            config.setUsername(props.getProperty("spring.datasource.username"));
            config.setPassword(props.getProperty("spring.datasource.password"));
//            config.addDataSourceProperty("cachePrepStmts", "true");
//            config.addDataSourceProperty("prepStmtCacheSize", "250");
//            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        }
        return config;
    }

    public DataSource getDataSource() {
        return new HikariDataSource(getConfig());
    }

    private static Properties loadProperties(String fileName) {
        Properties props = new Properties();
        try (InputStream is = DataSourceFactory.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return props;
    }
}
