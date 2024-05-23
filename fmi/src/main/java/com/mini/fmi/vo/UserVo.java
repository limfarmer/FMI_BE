package com.mini.fmi.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String id; // Primary Key
    private String pw;
    private String email;
    private Date joinDate;
    private String name;
    private String nickname;
}
