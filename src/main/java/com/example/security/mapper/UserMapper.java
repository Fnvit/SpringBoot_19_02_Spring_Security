package com.example.security.mapper;

import com.example.security.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String id);
    void insertUser(UserDTO userDTO); // 유저 회원가입
    UserDTO selectUserBySNSId(String snsId);
}
