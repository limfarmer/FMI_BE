package com.mini.fmi.dao;

import com.mini.fmi.common.Common;
import com.mini.fmi.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
            pstmt.setDate(6, new java.sql.Date(user.getJoinDate().getTime()));
            result = pstmt.executeUpdate();
            System.out.println("회원 가입 DB 결과 확인 : " + result);
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
        String id = "";
        String sql = "SELECT USER_ID FROM USERS WHERE EMAIL = ? AND NAME = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getString("USER_ID");
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    // 회원 탈퇴
    public boolean userDelete(String id) {
        int result = 0;
        String sql = "DELETE FROM USERS WHERE USER_ID = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
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
        UserVo user = null;
        String sql = "SELECT * FROM USERS WHERE USER_ID = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
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
}
