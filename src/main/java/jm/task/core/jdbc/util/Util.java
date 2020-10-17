package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection getMySQLConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "coreTaskDB";
        String userName = "Kostya";
        String password = "carisma1997";

        if (connection == null) {
            connection = getMySQLConnection(hostName, dbName, userName, password);
        }
        return connection;
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=UTC";
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().
                                applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}


/*
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private HibernateUtil() {}
    public static SessionFactory getSessionFactory() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=Europe/Moscow");
        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "root");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("show_sql", "true");
//        prop.setProperty("hibernate.default_schema", "facingissuesonitdb");
        prop.setProperty("hibernate.hbm2ddl.auto", "create");
        sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(prop).buildSessionFactory();
        return sessionFactory;
    }
}
/*
public static SessionFactory getSessionFactoryHibernate() {
        if(factory == null) {
            try {
                try {
                    Configuration configuration = new Configuration();
                    configuration.addAnnotatedClass(User.class);
​
                    Properties settings = new Properties();
                    // Почему без драйвера работает ?????
                    //settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver"); //AvailableSettings
​
                    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/my_schema?useSSL=false");
                    settings.put(Environment.USER, "root");
                    settings.put(Environment.PASS, "1rt7");
​
                    // Что дает диалект ??
                    // settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
​
                    settings.put(Environment.SHOW_SQL, "true");
                    // settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
​
                    // авто-генерация таблиц ????
                    //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                    //settings.put(Environment.HBM2DDL_AUTO, SchemaAutoTooling.CREATE.name().toLowerCase());
                    settings.put(Environment.AUTOCOMMIT, "true");
​
                    configuration.setProperties(settings);
​
                    serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build();
​
                    factory = configuration.buildSessionFactory(serviceRegistry);
​
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                if(serviceRegistry != null) {
                    StandardServiceRegistryBuilder.destroy(serviceRegistry);
                }
            }
        }
        return factory;
*/