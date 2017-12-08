package com.javamog.potapov.service;

import com.javamog.potapov.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category getCategoryByName(String name);
}
