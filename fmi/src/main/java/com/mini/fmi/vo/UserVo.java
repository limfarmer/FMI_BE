package com.mini.fmi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String id; // Primary Key
    private String pw;
    private String email;
    private Date joinDate;
    private String name;
    private String nickname;
}