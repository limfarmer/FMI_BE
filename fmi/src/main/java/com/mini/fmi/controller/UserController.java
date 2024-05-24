package com.mini.fmi.controller;

import com.mini.fmi.service.MyPageService;
import com.mini.fmi.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000") // CORS 설정 추가
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final MyPageService myPageService;

    public UserController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> userReg(@RequestBody UserVo userVo) {
        boolean isTrue = myPageService.userInsert(userVo);
        return ResponseEntity.ok(isTrue);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserVo userVo) {
        log.info("userVo: {}", userVo.getId());
        log.info("userVo: {}", userVo.getPw());
        boolean isTrue = myPageService.loginCheck(userVo.getId(), userVo.getPw());
        return ResponseEntity.ok(isTrue);
    }

    @GetMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam String email, @RequestParam String name) {
        String list = myPageService.findId(email, name);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> withdrawal(@RequestBody Map<String, String> data) {
        String getId = data.get("id");
        boolean isTrue = myPageService.userDelete(getId);
        return ResponseEntity.ok(isTrue);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserVo> getUser(@PathVariable String userId) {
        UserVo user = myPageService.getUserInfo(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody UserVo userVo) {
        userVo.setId(userId);
        boolean isUpdated = myPageService.updateUserInfo(userVo);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PostMapping("/deactivate")
    public ResponseEntity<Boolean> deactivateUser(@RequestBody Map<String, String> data) {
        String userId = data.get("id");
        boolean isDeactivated = myPageService.deactivateUser(userId);
        return ResponseEntity.ok(isDeactivated);
    }

}
