/*
 * Copyright 2019 slavb.
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
package ru.ilb.jparestresource;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.cxf.Bus;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 *
 * @author slavb
 */
@SpringBootApplication
@ComponentScan
//@EntityScan(value = {"ru.ilb.jparestresource.model", "ru.ilb.jparestresource.converters"}, basePackageClasses = {UUIDConverterBytes.class})
//@EnableJpaRepositories(basePackages = "ru.ilb.jparestresource.repositories", repositoryBaseClass = CacheableJpaRepositoryImpl.class)
//@PropertySource("application.properties")
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableCaching(mode = AdviceMode.ASPECTJ)
public class Application extends JpaBaseConfiguration {

    @Autowired
    private Bus bus;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public Application(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManager, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> map = new HashMap<>();
        map.put(PersistenceUnitProperties.WEAVING, "static");
        map.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.NONE);
        map.put(PersistenceUnitProperties.BATCH_WRITING, "JDBC");
        map.put(PersistenceUnitProperties.BATCH_WRITING_SIZE, "1000");
        return map;
    }
}
