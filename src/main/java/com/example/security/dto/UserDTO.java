package com.example.security.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails {
    private String id; // varchar(15) pk
    private String password; // char 60 not null
    private String nickname; // varchar(10) null
    private String phone; // 010-1111-1111 char(13) not null
    private String email; // varchar(100) not null
    private LocalDate registerDate; // date

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return this.id;
    }
}
