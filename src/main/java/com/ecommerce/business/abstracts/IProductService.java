package com.ecommerce.business.abstracts;

public interface IProductService {
    Product save (Product product);
    Product get (ind id);

    Page<Product> cursor (int page, int pageSize);

    Product update(Product product);

    boolean delete(int id);
}
