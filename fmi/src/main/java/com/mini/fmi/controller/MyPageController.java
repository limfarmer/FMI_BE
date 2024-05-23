package com.mini.fmi.controller;

import com.mini.fmi.service.MyPageService;
import com.mini.fmi.vo.UserVo;
import com.mini.fmi.vo.FollowVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypage")
@CrossOrigin(origins = "http://localhost:3000")
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserVo> getUserInfo(@RequestParam String userId) {
        UserVo user = myPageService.getUserInfo(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user")
    public ResponseEntity<Boolean> updateUserInfo(@RequestBody UserVo userVo) {
        boolean result = myPageService.updateUserInfo(userVo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/follow")
    public ResponseEntity<List<FollowVo>> getFollowList(@RequestParam String userId) {
        List<FollowVo> list = myPageService.getFollowList(userId);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/follow/{teamId}")
    public ResponseEntity<Boolean> unfollowTeam(@PathVariable String teamId, @RequestParam String userId) {
        boolean result = myPageService.unfollowTeam(userId, teamId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String userId) {
        boolean result = myPageService.deleteUser(userId);
        return ResponseEntity.ok(result);
    }
}
