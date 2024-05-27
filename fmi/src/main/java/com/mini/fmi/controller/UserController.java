package com.mini.fmi.controller;

import com.mini.fmi.service.UserService;
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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> userReg(@RequestBody UserVo userVo) {
        boolean isTrue = userService.userInsert(userVo);
        return ResponseEntity.ok(isTrue);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserVo userVo) {
        log.info("userVo: {}", userVo.getId());
        log.info("userVo: {}", userVo.getPw());
        boolean isTrue = userService.loginCheck(userVo.getId(), userVo.getPw());
        return ResponseEntity.ok(isTrue);
    }

    @GetMapping("/findId")
    public ResponseEntity<String> findId(@RequestParam String email, @RequestParam String name) {
        String userId = userService.findId(email, name);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 사용자 정보를 찾을 수 없습니다.");
        }
    }

    @GetMapping("/findPassword")
    public ResponseEntity<String> findPassword(@RequestParam String email, @RequestParam String nickname) {
        String password = userService.findPassword(email, nickname);
        if (password != null) {
            return ResponseEntity.ok(password);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 사용자 정보를 찾을 수 없습니다.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> withdrawal(@RequestBody Map<String, String> data) {
        String getId = data.get("id");
        boolean isTrue = userService.userDelete(getId);
        return ResponseEntity.ok(isTrue);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserVo> getUser(@PathVariable String userId) {
        UserVo user = userService.getUserInfo(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody UserVo userVo) {
        userVo.setId(userId);
        boolean isUpdated = userService.updateUserInfo(userVo);
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("/deactivate")
    public ResponseEntity<Boolean> deactivateUser(@RequestBody Map<String, String> data) {
        String userId = data.get("id");
        boolean isDeactivated = userService.deactivateUser(userId);
        return ResponseEntity.ok(isDeactivated);
    }
}
