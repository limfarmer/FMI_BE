package com.mini.fmi.service;

import com.mini.fmi.dao.UserDao;
import com.mini.fmi.dao.FollowDao;
import com.mini.fmi.vo.UserVo;
import com.mini.fmi.vo.FollowVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

    private final UserDao userDao;
    private final FollowDao followDao;

    public MyPageService(UserDao userDao, FollowDao followDao) {
        this.userDao = userDao;
        this.followDao = followDao;
    }

    public UserVo getUserInfo(String userId) {
        return userDao.getUserById(userId);
    }

    public boolean updateUserInfo(UserVo userVo) {
        return userDao.updateUser(userVo);
    }

    public List<FollowVo> getFollowList(String userId) {
        return followDao.getFollowList(userId);
    }

    public boolean unfollowTeam(String userId, String teamId) {
        return followDao.unfollowTeam(userId, teamId);
    }

    public boolean deleteUser(String userId) {
        return userDao.userDelete(userId); // 여기를 수정했습니다.
    }
}
