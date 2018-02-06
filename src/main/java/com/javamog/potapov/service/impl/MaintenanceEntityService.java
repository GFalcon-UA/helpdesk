package com.javamog.potapov.service.impl;

import com.javamog.potapov.dao.CategoryDAO;
import com.javamog.potapov.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaintenanceEntityService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> getCategories(){
        return categoryDAO.findAll();
    }
}
