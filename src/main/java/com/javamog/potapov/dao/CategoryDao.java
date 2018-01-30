package com.javamog.potapov.dao;

import com.javamog.potapov.model.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getCategories();

    Category getCategoryByName(String name);
}
