package com.mini.fmi.controller;


import com.mini.fmi.dao.FollowDao;
import com.mini.fmi.vo.FollowVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User단 만들어지면 옮길 예정
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/follow")
@Slf4j
public class FollowController {

    private final FollowDao followDao;

    public FollowController(FollowDao followDao) {
        this.followDao = followDao;
    }

    @GetMapping("/list")
    public ResponseEntity<List<FollowVo>> getFollowList(@RequestParam String userId){
        List<FollowVo> list = followDao.getFollowList(userId);
        return ResponseEntity.ok(list);
    }

    // DELETE 방식, 팔로우 해제
//    @DeleteMapping("/{userId}/{teamId}")
//    public ResponseEntity<Boolean> unfollowTeam(@PathVariable String userId, @PathVariable String teamId) {
//        System.out.println("o");
//        boolean isTrue = followDao.unfollowTeam(userId, teamId);
//        return ResponseEntity.ok(isTrue);
//    }
}