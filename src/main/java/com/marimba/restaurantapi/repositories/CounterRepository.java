package com.marimba.restaurantapi.repositories;

import com.marimba.restaurantapi.entities.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter,Long> {
    @Query("SELECT COALESCE(MAX(c.number), 100000) FROM Counter c")
    int findMaxNumber();

}
