package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Statement statement;

    /*{
        try {
            statement = Util.getMySQLConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS myIdolsTable(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(30), lastName varchar(30), age int)";
        try {
            statement.executeUpdate(createTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("drop table myIdolsTable");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.executeUpdate("INSERT myIdolsTable(name, lastName, age) " +
                    "VALUE ('" + name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate("DELETE FROM myIdolsTable WHERE Id = " + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
            try {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM myIdolsTable");
                while(resultSet.next()){
                    User user = new User(resultSet.getString(2),
                            resultSet.getString(3), resultSet.getByte(4));
                    user.setId(resultSet.getLong(1));
                    users.add(user);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return users;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("TRUNCATE TABLE myIdolsTable");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}