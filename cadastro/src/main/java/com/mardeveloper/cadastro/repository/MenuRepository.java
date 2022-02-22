package com.mardeveloper.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mardeveloper.cadastro.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}