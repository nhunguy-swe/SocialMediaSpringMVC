package com.social.dao;

import com.social.entity.Post;
import com.social.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    // Khai báo các đối tượng JDBC
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    // Hàm đóng kết nối tự động để tránh tràn bộ nhớ
    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. Lấy tất cả bài viết
    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        String query = "SELECT p.*, u.username FROM posts p "
                + "JOIN users u ON p.user_id = u.id "
                + "ORDER BY p.created_at DESC";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapPost(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    // 2. Tìm kiếm
    public List<Post> searchPosts(String txtSearch) {
        List<Post> list = new ArrayList<>();
        String query = "SELECT p.*, u.username FROM posts p "
                + "JOIN users u ON p.user_id = u.id "
                + "WHERE p.title LIKE ? "
                + "OR p.body LIKE ? "
                + "OR u.username LIKE ? "
                + "ORDER BY p.created_at DESC";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);

            String searchParam = "%" + txtSearch + "%";
            ps.setString(1, searchParam);
            ps.setString(2, searchParam);
            ps.setString(3, searchParam);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapPost(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    // 3. News Feed
    public List<Post> getNewsFeed(int currentUserId) {
        List<Post> list = new ArrayList<>();
        String query = "SELECT p.*, u.username FROM posts p "
                + "JOIN users u ON p.user_id = u.id "
                + "WHERE p.user_id IN (SELECT followed_user_id FROM follows WHERE following_user_id = ?) "
                + "OR p.user_id = ? "
                + "ORDER BY p.created_at DESC";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapPost(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    // 4. Tạo bài viết mới
    public void createPost(String title, String body, int userId, String status) {
        String query = "INSERT INTO posts (title, body, user_id, status, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, body);
            ps.setInt(3, userId);
            ps.setString(4, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Hàm phụ chuyển đổi dữ liệu từ ResultSet sang Object Post
    private Post mapPost(ResultSet rs) throws Exception {
        Post p = new Post();
        p.setId(rs.getInt("id"));
        p.setTitle(rs.getString("title"));
        p.setBody(rs.getString("body"));
        p.setUserId(rs.getInt("user_id"));
        p.setStatus(rs.getString("status"));
        p.setCreatedAt(rs.getTimestamp("created_at"));
        p.setAuthorName(rs.getString("username"));
        return p;
    }
}