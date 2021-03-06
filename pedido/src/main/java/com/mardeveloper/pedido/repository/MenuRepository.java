package com.mardeveloper.pedido.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mardeveloper.pedido.entity.Menu;

import java.util.Optional;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m WHERE m.idMenu = ?1 AND m.idRestaurant = ?2")
    Optional<Menu> findByIdMenuAndIdRestaurant(Long idMenu, Long idRestaurant);

}
