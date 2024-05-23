package com.mini.fmi.controller;

import com.mini.fmi.dao.UserDao;
import com.mini.fmi.vo.UserVo;
import com.mini.fmi.service.MyPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final MyPageService myPageService;

    public UserController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    // POST 방식, 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Boolean> userReg(@RequestBody UserVo userVo) {
        UserDao dao = new UserDao();
        boolean isTrue = dao.userInsert(userVo);
        return ResponseEntity.ok(isTrue);
    }

    // POST 방식, 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserVo userVo) {
        log.info("userVo: {}", userVo.getId());
        log.info("userVo: {}", userVo.getPw());
        UserDao userDao = new UserDao();
        boolean isTrue = userDao.loginCheck(userVo.getId(), userVo.getPw());
        return ResponseEntity.ok(isTrue);
    }

    // GET 방식, 이메일 & 이름으로 찾기
    @GetMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam String email, @RequestParam String name) {
        UserDao userDao = new UserDao();
        String list = userDao.findId(email, name);
        return ResponseEntity.ok(list);
    }

    // DELETE 방식(POST 방식이 나을지 고민 중), 회원 탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> withdrawal(@RequestBody Map<String, String> data) {
        String getId = data.get("id"); // 중복되면 안 되므로 유일한 식별자 이용
        UserDao userDao = new UserDao();
        boolean isTrue = userDao.userDelete(getId);
        return ResponseEntity.ok(isTrue);
    }
}
