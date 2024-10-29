package com.marimba.restaurantapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marimba.restaurantapi.entities.Turn;


@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {
	
    Optional<Turn> findById(Long id);
	

}
