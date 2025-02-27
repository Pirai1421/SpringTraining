package com.example.restaurant.dao;

import com.example.restaurant.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemDao extends JpaRepository<MenuItem, Long> {
}