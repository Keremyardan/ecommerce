package com.ecommerce.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveRequest {
    private  String name;
    private double prc;
    private int stock;
    private int supplierId;
    private int categoryId;
}
