package com.mini.fmi.dao;

import com.mini.fmi.common.Common;
import com.mini.fmi.vo.FollowVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowDao {

    public List<FollowVo> getFollowList(String userId) {
        List<FollowVo> list = new ArrayList<>();
        String sql = "SELECT * FROM FOLLOW WHERE USER_ID = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String followUserId = rs.getString("follow_id");
                String followId = rs.getString("user_id");
                String followTeamName = rs.getString("team_name");
                FollowVo vo = new FollowVo(followUserId, followId, followTeamName);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean unfollowTeam(String userId, String teamId) {
        int result = 0;
        String sql = "DELETE FROM FOLLOW WHERE USER_ID = ? AND TEAM_ID = ?";
        try {
            Connection conn = Common.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, teamId);
            result = pstmt.executeUpdate();
            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == 1;
    }
}
