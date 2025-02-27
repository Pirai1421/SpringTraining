package com.example.restaurant.service;

import com.example.restaurant.dao.MenuItemDao;
import com.example.restaurant.entity.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuItemServiceImpl {
    private final MenuItemDao menuItemDao;

    @Autowired
    public MenuItemServiceImpl(MenuItemDao menuItemDao) {
        this.menuItemDao = menuItemDao;
    }

    public List<MenuItem> findAll() {
        return menuItemDao.findAll();
    }

    public MenuItem findById(long id) {
        return menuItemDao.findById(id).orElse(null);
    }

    @Transactional
    public MenuItem save(MenuItem menuItem) {
        return menuItemDao.save(menuItem);
    }

    @Transactional
    public void deleteById(long id) {
        menuItemDao.deleteById(id);
    }

    public List<MenuItem> findAllById(List<Long> ids) {
        return menuItemDao.findAllById(ids);
    }
}