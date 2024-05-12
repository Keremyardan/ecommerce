package com.ecommerce.api;

import com.ecommerce.business.abstracts.ICategoryService;
import com.ecommerce.business.abstracts.IProductService;
import com.ecommerce.business.abstracts.ISupplierService;
import com.ecommerce.core.config.modelMapper.IModelMapperService;
import com.ecommerce.core.config.result.ResultData;
import com.ecommerce.core.config.utilities.ResultHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final IProductService productService;

    private final IModelMapperService modelMapper;

    private final ICategoryService categoryService;

    private final ISupplierService supplierService;

    public ProductController(IProductService productService, IModelMapperService modelMapper, ICategoryService categoryService, ISupplierService supplierService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ProductResponse> save (@Valid @RequestBody ProductSaveRequest productSaveRequest) {
        Product saveProduct = this.modelMapper.forRequest().map(productSaveRequest, Product.class);

        Category category = this.categoryService.get(productSaveRequest.getCategoryId());

        Supplier supplier = this.supplierService.get(productSaveRequest.getSupplierId());
        saveProduct.setSupplier(supplier);

        this.productService.save(saveProduct);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveProduct, ProductResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ProductResponse> get(@PathVariable("id") int id){
        Product product = this.productService.get(id);

        return ResultHelper.success(this.modelMapper.forResponse().map(product, ProductResponse.class));
    }

    @GetMapping("/{id}/supplier")
    @ResponseStatus(HttpStatus.OK)
    public  ResultData<ProductResponse> getSupplier(@PathVariable("id") int id) {
        return ResultHelper.success(this.modelMapper.forResponse().map(product.getSupplier, ProductResponse.class));
    }

}
