package com.mini.fmi.dao;

import com.mini.fmi.common.Common;
import com.mini.fmi.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    // 회원 가입
    public boolean userInsert(UserVo user) {
        int result = 0;
        String sql = "INSERT INTO USERS (USER_ID, PASSWORD, NAME, EMAIL, NICKNAME, JOIN_DATE) VALUES (?,?,?,?,?,?)";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPw());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getNickname());
            pstmt.setDate(6, new java.sql.Date(System.currentTimeMillis())); // 현재 날짜를 joinDate로 설정
            result = pstmt.executeUpdate();
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == 1;
    }

    // 로그인
    public boolean loginCheck(String id, String pw) {
        boolean loginOk = false;
        String sql = "SELECT * FROM USERS WHERE USER_ID = ? AND PASSWORD = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            ResultSet rs = pstmt.executeQuery();
            loginOk = rs.next();
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginOk;
    }

    // 이메일 & 이름으로 ID 찾기
    public String findId(String email, String name) {
        String userId = null;
        String sql = "SELECT USER_ID FROM USERS WHERE EMAIL = ? AND NAME = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString("USER_ID");
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    // 이메일 & 닉네임으로 비밀번호 찾기
    public String findPassword(String email, String nickname) {
        String password = null;
        String sql = "SELECT PASSWORD FROM USERS WHERE EMAIL = ? AND NICKNAME = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, nickname);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                password = rs.getString("PASSWORD");
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    // 회원 탈퇴
    public boolean userDelete(String id) {
        int result = 0;
        String sql = "DELETE FROM USERS WHERE USER_ID = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id.replace("\"", ""));
            result = pstmt.executeUpdate();
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == 1;
    }

    // 사용자 정보 가져오기
    public UserVo getUserById(String userId) {
        System.out.println(userId+ "! userId from dao start");
        UserVo user = null;
        String sql = "SELECT * FROM USERS WHERE USER_ID = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId.replace("\"", ""));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserVo(
                        rs.getString("USER_ID"),
                        rs.getString("PASSWORD"),
                        rs.getString("EMAIL"),
                        rs.getDate("JOIN_DATE"),
                        rs.getString("NAME"),
                        rs.getString("NICKNAME")
                );
                System.out.println(user.getId() + " user id from dao end");
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // 사용자 정보 업데이트
    public boolean updateUser(UserVo userVo) {
        int result = 0;
        String sql = "UPDATE USERS SET PASSWORD = ?, EMAIL = ?, NAME = ?, NICKNAME = ? WHERE USER_ID = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userVo.getPw());
            pstmt.setString(2, userVo.getEmail());
            pstmt.setString(3, userVo.getName());
            pstmt.setString(4, userVo.getNickname());
            pstmt.setString(5, userVo.getId());
            result = pstmt.executeUpdate();
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == 1;
    }

    // 사용자 상태 업데이트 (비활성화)
    public boolean updateUserStatus(String userId, String status) {
        int result = 0;
        String sql = "UPDATE USERS SET STATUS = ? WHERE USER_ID = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setString(2, userId.replace("\"", ""));
            result = pstmt.executeUpdate();
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == 1;
    }

    // 비활성화된 사용자 조회
    public List<UserVo> findUsersByStatus(String status) {
        List<UserVo> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS WHERE STATUS = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserVo user = new UserVo(
                        rs.getString("USER_ID"),
                        rs.getString("PASSWORD"),
                        rs.getString("EMAIL"),
                        rs.getDate("JOIN_DATE"),
                        rs.getString("NAME"),
                        rs.getString("NICKNAME")
                );
                users.add(user);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
