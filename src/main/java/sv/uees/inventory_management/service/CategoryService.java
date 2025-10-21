package sv.uees.inventory_management.service;


import sv.uees.inventory_management.model.dao.CategoryDao;
import sv.uees.inventory_management.model.entity.CategoryEntity;

import java.util.List;

public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryDao.findAll();
    }
}