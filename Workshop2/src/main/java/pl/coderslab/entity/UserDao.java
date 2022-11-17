package pl.coderslab.entity;

import pl.coderslab.DbUtil;

import java.sql.*;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO workshop2.users(email, username, password) VALUES (?, ?,?)";
    private static final String READ_USER_QUERY = "Select * from workshop2.users where id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE workshop2.users set ? = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM workshop2.users where id = ?";
    private static final String FINDALL_USER_QUERY = "Select * from workshop2.users";

    public static void main(String[] args) {

    }

    public User create(User user) {
        try (Connection connection = DbUtil.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
