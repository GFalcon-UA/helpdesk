package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.CategoryDao;
import com.javamog.potapov.model.Category;
import com.javamog.potapov.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }
}
