package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();


    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, "
            + "name VARCHAR(255), last_name VARCHAR(255), age int)";
    private static final String ADD_USER = "INSERT INTO users (name, last_name, age) Values (?, ?, ?)";
    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS users";
    private static final String DELETE_USERS_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String CLEAN_TABLE = "TRUNCATE TABLE users";


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(CREATE_TABLE).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(DELETE_TABLE);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            System.out.println("User с именем – " + name + " добавлен в базу данных ");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(DELETE_USERS_BY_ID);
            query.setParameter(1, id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            listUser = (List<User>) session.createQuery("From " + User.class.getSimpleName().toString()).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(CLEAN_TABLE);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
