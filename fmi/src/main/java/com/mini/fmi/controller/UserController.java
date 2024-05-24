package com.mini.fmi.controller;

import com.mini.fmi.service.MyPageService;
import com.mini.fmi.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final MyPageService myPageService;

    public UserController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    // POST 방식, 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Boolean> userReg(@RequestBody UserVo userVo) {
        boolean isTrue = myPageService.userInsert(userVo);
        return ResponseEntity.ok(isTrue);
    }

    // POST 방식, 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserVo userVo) {
        log.info("userVo: {}", userVo.getId());
        log.info("userVo: {}", userVo.getPw());
        boolean isTrue = myPageService.loginCheck(userVo.getId(), userVo.getPw());
        return ResponseEntity.ok(isTrue);
    }

    // GET 방식, 이메일 & 이름으로 찾기
    @GetMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam String email, @RequestParam String name) {
        String list = myPageService.findId(email, name);
        return ResponseEntity.ok(list);
    }

    // DELETE 방식(POST 방식이 나을지 고민 중), 회원 탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> withdrawal(@RequestBody Map<String, String> data) {
        String getId = data.get("id"); // 중복되면 안 되므로 유일한 식별자 이용
        boolean isTrue = myPageService.userDelete(getId);
        return ResponseEntity.ok(isTrue);
    }

    // GET 방식, 사용자 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserVo> getUser(@PathVariable String userId) {
        UserVo user = myPageService.getUserInfo(userId);
        return ResponseEntity.ok(user);
    }

    // PUT 방식, 사용자 정보 수정
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody UserVo userVo) {
        userVo.setId(userId); // userVo에 userId를 설정합니다.
        boolean isUpdated = myPageService.updateUserInfo(userVo);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
