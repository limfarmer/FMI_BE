package com.mini.fmi.service;

import com.mini.fmi.dao.FollowDao;
import com.mini.fmi.vo.FollowVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

    private final FollowDao followDao;

    public MyPageService(FollowDao followDao) {
        this.followDao = followDao;
    }

    public List<FollowVo> getFollowList(String userId) {
        return followDao.getFollowList(userId);
    }

    public boolean unfollowTeam(String teamId, String userId) {
        return followDao.unfollowTeam(teamId, userId);
    }
}
