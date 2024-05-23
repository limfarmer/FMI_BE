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
    
    @GetMapping("/list")
    public ResponseEntity<List<FollowVo>> getFollowList(@RequestParam String userId){
        System.out.println("ㅎㅇ");
        FollowDao followDao = new FollowDao();
        List<FollowVo> list = followDao.getFollowList(userId);
        return ResponseEntity.ok(list);
    }
//    @GetMapping("/list")
//    public ResponseEntity<List<BoardDto>> boardList() {
//        List<BoardDto> list = boardService.getBoardList();
//        return ResponseEntity.ok(list);
//    }
}
