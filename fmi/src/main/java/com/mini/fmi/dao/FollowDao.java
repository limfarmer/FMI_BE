package com.mini.fmi.dao;

import com.mini.fmi.common.Common;
import com.mini.fmi.vo.FollowVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FollowDao {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    public List<FollowVo> getFollowList(String id){
        List<FollowVo> list =  new ArrayList<>();
        System.out.println(id + "아이디");
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM FOLLOW WHERE USER_ID ='"+ id + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                String followUserId = rs.getString("follow_id");
                String followId = rs.getString("user_id");
                String followTeamName = rs.getString("team_name");
                FollowVo vo = new FollowVo();
                vo.setFollowId(followId);
                vo.setUserId(followUserId);
                vo.setTeamName(followTeamName);
                list.add(vo);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
      return list;
    }
}
