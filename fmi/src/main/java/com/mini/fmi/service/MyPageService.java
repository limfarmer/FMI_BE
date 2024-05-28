package com.mini.fmi.service;

import com.mini.fmi.dao.MypageDao;
import com.mini.fmi.vo.FollowVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

    private final MypageDao mypageDao;

    public MyPageService(MypageDao mypageDao) {
        this.mypageDao = mypageDao;
    }

    public List<FollowVo> getFollowList(String userId) {
        return mypageDao.getFollowList(userId);
    }

    public boolean unfollowTeam(String teamId, String userId) {
        return mypageDao.unfollowTeam(teamId, userId);
    }
}
