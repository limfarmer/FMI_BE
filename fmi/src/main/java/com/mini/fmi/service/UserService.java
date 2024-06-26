package com.mini.fmi.service;

import com.mini.fmi.dao.UserDao;
import com.mini.fmi.dao.MypageDao;
import com.mini.fmi.vo.UserVo;
import com.mini.fmi.vo.FollowVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;
    private final MypageDao mypageDao;

    public UserService(UserDao userDao, MypageDao mypageDao) {
        this.userDao = userDao;
        this.mypageDao = mypageDao;
    }

    public UserVo getUserInfo(String userId) {
        return userDao.getUserById(userId);
    }

    public boolean updateUserInfo(UserVo userVo) {
        return userDao.updateUser(userVo);
    }

    public List<FollowVo> getFollowList(String userId) {
        return mypageDao.getFollowList(userId);
    }

    public boolean unfollowTeam(String userId, String teamId) {
        return mypageDao.unfollowTeam(userId, teamId);
    }

    public boolean deleteUser(String userId) {
        return userDao.userDelete(userId);
    }

    public boolean userInsert(UserVo userVo) {
        return userDao.userInsert(userVo);
    }

    public boolean loginCheck(String id, String pw) {
        return userDao.loginCheck(id, pw);
    }

    public String findId(String email, String name) {
        return userDao.findId(email, name);
    }

    public String findPassword(String email, String nickname) {
        return userDao.findPassword(email, nickname);
    }

    public boolean userDelete(String userId) {
        return userDao.userDelete(userId);
    }

    public boolean deactivateUser(String userId) {
        return userDao.updateUserStatus(userId, "INACTIVE");
    }
}
