package com.javamog.potapov.dao.Impl;

import com.javamog.potapov.dao.CategoryDAO;
import com.javamog.potapov.domain.Category;
import com.javamog.potapov.parent.dao.GenericEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends GenericEntityDao<Category> implements CategoryDAO {
    protected CategoryDaoImpl() {
        super(Category.class);
    }
}
