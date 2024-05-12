package com.ecommerce.business.abstracts;

import com.ecommerce.entities.Supplier;

public interface ISupplierService {
    Supplier save (Supplier supplier);

    Supplier save(Supplier supplier);

    Supplier get(int id);

    Page<Supplier> cursor (int page, int pageSize);

    Supplier update(Supplier supplier);

    boolean delete (int id);
}
