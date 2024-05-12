package com.ecommerce.api;

import com.ecommerce.business.abstracts.ICategoryService;
import com.ecommerce.core.config.modelMapper.IModelMapperService;
import com.ecommerce.core.config.result.Result;
import com.ecommerce.core.config.result.ResultData;
import com.ecommerce.core.config.utilities.ResultHelper;
import com.ecommerce.dto.request.category.CategoryUpdateRequest;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    private final IModelMapperService modelmapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelmapper) {
        this.categoryService = categoryService;
        this.modelmapper = modelmapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category saveCategory = this.modelmapper.forRequest().map(categorySaveRequest,Category.class);
        this.categoryService.save(saveCategory);
        return ResultHelper.created(this.modelmapper.forResponse().map(saveCategory,CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id){
        Category category = this.categoryService.get(id);

        return ResultHelper.success(this.modelmapper.forResponse().map(category, CategoryResponse.class));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor (
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Category> categoryPage = this.categoryService.cursor(page,pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelmapper.forResponse().map(category, CategoryResponse.class));

        return ResultHelper.cursor(categoryResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public  ResultData<CategoryResponse> update (@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        this.categoryService.get(categoryUpdateRequest.getId());
        Category updateCategory = this.modelmapper.forRequest().map(categoryUpdateRequest, Category.class);
        return ResultHelper.success(this.modelmapper.forResponse().map(updateCategory, CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.categoryService.delete(id);
        return ResultHelper.successResult();
    }

}
