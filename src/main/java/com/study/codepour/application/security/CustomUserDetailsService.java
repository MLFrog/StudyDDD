package com.study.codepour.application.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.codepour.infrastructure.jpa.entity.DataUserEntity;
import com.study.codepour.infrastructure.jpa.repository.UserDataJpaRepository;

import io.jsonwebtoken.lang.Assert;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserDataJpaRepository repository;

    @PostConstruct
    public void init() {
    	
    }
    
    @Override	
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
        	DataUserEntity userData = this.repository.findByUsername(username);
        	
        	Assert.notNull(userData, "계정이 없습니다.");
        	
            List<GrantedAuthority> authList = new ArrayList<>();
            authList.add(new SimpleGrantedAuthority("ADMIN"));
            
            return new User(userData.getUsername(), userData.getPassword(), true, true, true, true, authList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}