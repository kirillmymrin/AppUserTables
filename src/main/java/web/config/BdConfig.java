package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
@EnableJpaRepositories("web")

public class BdConfig {


    private final Environment env;

    public BdConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource DataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUrl(env.getRequiredProperty("db.url"));
        ds.setUsername(env.getRequiredProperty("db.username"));
        ds.setPassword(env.getRequiredProperty("db.password"));
        ds.setInitialSize(Integer.parseInt(env.getRequiredProperty("db.initialSize")));
        ds.setMaxIdle(Integer.parseInt(env.getRequiredProperty("db.minIdle")));
        ds.setMaxIdle(Integer.parseInt(env.getRequiredProperty("db.maxIdle")));
        ds.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getRequiredProperty("db.timeBetweenEvictionRunMills")));
        ds.setMinEvictableIdleTimeMillis(Long.parseLong(env.getRequiredProperty("dm.minEvictableIdleTimeMills")));
        ds.setTestOnBorrow(Boolean.parseBoolean(env.getRequiredProperty("db.testOnBorrow")));
        ds.setValidationQuery(env.getProperty("db.validationQuery"));
        return ds;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(DataSource());
        em.setPackagesToScan("web.models");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        em.setJpaProperties(hibernateProperties);
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
