package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session;
    Statement statement;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS somepeople(" +
                "id int PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(30)," +
                "lastName VARCHAR(30)," +
                "age int)").executeUpdate();
        tx.commit();
        session.close();
    }

 /*   @Override
    public void dropUsersTable() {
        try {
            statement = Util.getMySQLConnection().createStatement();
            ResultSet rs = statement.executeQuery("Show tables");
            while (rs.next()) {
                if (rs.getString(1).equals("somepeople")) {
                    session.createSQLQuery("DROP TABLE IF EXISTS somepeople").executeUpdate();
                    System.out.println("Удалена таблица somepeople");
                    break;
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public void dropUsersTable() {
        session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS somepeople").executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        tx.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        tx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        tx.commit();
        session.close();
        return users;
    }
/*@Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            userList = session.createQuery("from User order by name").list();
            session.getTransaction().commit();
        }
        return userList;
    }*/
    @Override
    public void cleanUsersTable() {
        session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        tx.commit();
        session.close();
    }
}