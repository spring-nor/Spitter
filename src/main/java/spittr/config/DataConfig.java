package spittr.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.context.WebApplicationContext;


import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class DataConfig {
//    private final Logger logger = LogManager.getLogger(DataConfig.class);
//    @Autowired
//    WebApplicationContext applicationContext;
//    @Value("${env}")
//    String env;

    @Value("${spitter.entity.package}")
    String spitter_entity_package;

    @Value("${spitter.db.driver}")
    String spitter_db_driver;

    @Value("${spitter.db.url}")
    String spitter_db_url;

    @Value("${spitter.db.username}")
    String spitter_db_username;

    @Value("${spitter.db.password}")
    String spitter_db_password;

    @Value("${hibernate.dialectl}")
    String hibernate_dialect;

    @Value("${hibernate.show_sql}")
    String hibernate_show_sql;

    @Value("${hibernate.hbm2ddl.auto}")
    String hibernate_hbm2ddl_auto;


    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernate_dialect);
        properties.put("hibernate.show_sql", hibernate_show_sql);
        properties.put("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//        Resource config = new ClassPathResource("hibernate.cfg.xml");
//        sessionFactory.setConfigLocation(config);

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(spitter_entity_package);
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {

//        logger.debug("getActiveProfiles: " + applicationContext.getEnvironment().getActiveProfiles()[0]);
//        logger.debug(">>>>>>>>>>> env : " + env);

        BasicDataSource ds = new BasicDataSource();

        // Driver class name
        ds.setDriverClassName(spitter_db_driver);
        // Set URL
        ds.setUrl(spitter_db_url);
        // Set username & password
        ds.setUsername(spitter_db_username);
        ds.setPassword(spitter_db_password);
        return ds;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory);
        return tm;
    }

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .build();
//    }

//    @Bean
//    public JdbcOperations jdbcTemplate() {
//        return new JdbcTemplate();
//    }

//    @Bean
//    public SpittleRepositoryImpl spittleRepository(JdbcOperations jdbc) {
//        return new SpittleRepositoryImpl(jdbc);
//    }

//    @Bean
//    public List<Spittle> spittles() {
//        return new ArrayList<Spittle>();
//    }

//    @Bean
//    public SpittleRepository spittleRepository() {
//        return new SpittleRepositoryImpl();
//    }
//
//    @Bean
//    public SpitterRepository spitterRepository() {
//        return new SpitterRepositoryImpl();
//    }

}
