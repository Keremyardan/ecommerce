package com.ecommerce.business.abstracts;


import jdk.jfr.Category;

public interface ICategoryService {
    Category save (Category category);
    Category get (int id);

    Page<Category> cursor(int page, int pageSize);

    Category update (Category category);

    boolean delete (int id);

}
