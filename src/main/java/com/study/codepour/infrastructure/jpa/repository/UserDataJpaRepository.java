package com.study.codepour.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.codepour.infrastructure.jpa.entity.DataUserEntity;

public interface UserDataJpaRepository extends JpaRepository<DataUserEntity, Long> {

}
