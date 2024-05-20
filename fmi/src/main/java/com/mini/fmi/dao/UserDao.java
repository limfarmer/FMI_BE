package com.mini.fmi.dao;

import com.mini.fmi.common.Common;
import com.mini.fmi.vo.UserVo;

import java.sql.*;
import java.util.Scanner;

public class UserDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    // 회원 가입
    public boolean userInsert(UserVo user) {
        int result = 0;
        String sql = "INSERT INTO USERS (USER_ID, PASSWORD, NAME, EMAIL, NICKNAME, JOIN_DATE) VALUES (?,?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPw());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getNickname());
            pstmt.setDate(6, (Date) user.getJoinDate());
            result = pstmt.executeUpdate();
            System.out.println("회원 가입 DB 결과 확인 : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }
    // 로그인
    public boolean loginCheck(String ID, String PW){
        boolean loginOk = false;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USERS WHERE ID = '" + ID + "' and PW='"+ PW + "'";
            rs = stmt.executeQuery(sql);

            if(rs.next()) loginOk = true;
            else loginOk = false;
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginOk; // 가입되어 있으면 true, 가입이 안 되어 있으면 false
    }
}