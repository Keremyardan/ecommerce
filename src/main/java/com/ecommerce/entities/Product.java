package com.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.data.repository.cdi.Eager;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id" , columnDefinition = "serial")
    private int id;


    @Column(name="product_name")
    private String name;

    @Column(name="product_price")
    private double prc;

    @Column(name="product_stock")
    private int stock;

    @ManyToOne()
    @JoinColumn(name = "product_supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier;

    @ManyToOne()
    @JoinColumn(name = "product_category_id", referencedColumnName = "category_id")
    private Category category;
}
