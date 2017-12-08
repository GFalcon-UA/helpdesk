package com.javamog.potapov.dao;

import com.javamog.potapov.model.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = (List<Category>) sessionFactory.openSession()
                .createQuery("FROM Category").getResultList();
        return categories;
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = (Category) sessionFactory.openSession()
                .createQuery("FROM Category where name = :name")
                .setParameter("name", name).getSingleResult();
        return category;
    }
}
