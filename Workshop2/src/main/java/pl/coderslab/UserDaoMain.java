package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

public class UserDaoMain {
    public static void main(String[] args) {
        User user = new User("Marcentilus", "marc@yahoo.com", "Rydygiera8");
        UserDao.create(user);
        System.out.println(user.getId());
    }
}
