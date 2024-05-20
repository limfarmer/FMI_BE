package com.mini.fmi.controller;

import com.mini.fmi.dao.UserDao;
import com.mini.fmi.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    // POST 방식, 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserVo userVo) {
        log.info("userVo: {}", userVo.getId());
        log.info("userVo: {}", userVo.getPw());
        UserDao userDao = new UserDao();
        boolean isTrue = userDao.loginCheck(userVo.getId(), userVo.getPw());
        return ResponseEntity.ok(isTrue);
    }

    // POST 방식, 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Boolean> userReg(@RequestBody UserVo userVo) {
        UserDao dao = new UserDao();
        boolean isTrue = dao.userInsert(userVo);
        return ResponseEntity.ok(isTrue);
    }
}
