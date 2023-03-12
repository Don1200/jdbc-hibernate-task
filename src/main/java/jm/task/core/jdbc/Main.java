package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("Leopold \"Butters\"","Stotch", (byte) 7);
        User user2 = new User("Eric \"Theodore\"","Cartman", (byte) 8);
        User user3 = new User("Kyle","Broflovski ", (byte) 9);
        User user4 = new User("Stan","Marsh", (byte) 10);


        Util.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();
       userDao.createUsersTable();

       userDao.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
       userDao.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
//        userDao.removeUserById(1);
//        userDao.cleanUsersTable();
        userDao.getAllUsers();
        System.out.println(userDao.getAllUsers());







        }
    }

