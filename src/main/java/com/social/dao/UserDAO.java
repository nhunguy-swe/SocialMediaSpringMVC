package com.social.dao;

import com.social.entity.User;
import com.social.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // 1. Đăng nhập: Kiểm tra username và password
    public User login(String user, String pass) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            conn = new DBUtils().getConnection(); // Kết nối CSDL
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu sai tài khoản/mật khẩu
    }

    // 2. Đăng ký: Thêm người dùng mới vào bảng users
    public void register(String user, String pass, String role) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, role);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Kiểm tra Username đã tồn tại hay chưa
    public User checkUserExist(String user) {
        String query = "SELECT * FROM users WHERE username = ?";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
