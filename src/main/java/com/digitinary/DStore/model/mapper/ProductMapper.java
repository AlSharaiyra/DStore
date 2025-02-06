package com.digitinary.DStore.model.mapper;

import com.digitinary.DStore.model.entity.Category;
import com.digitinary.DStore.model.entity.Product;
import com.digitinary.DStore.model.request.CreateProductRequest;
import com.digitinary.DStore.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "category", ignore = true)
    public abstract Product toProduct(CreateProductRequest request);

    @Mapping(target = "category_id", source = "category", qualifiedByName = "categoryToCategoryId")
    public abstract ProductResponse toView(Product product);

    @Named("categoryToCategoryId")
    protected Integer categoryToCategoryId(Category category){
        return category.getId();
    }
}
