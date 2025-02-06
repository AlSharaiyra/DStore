package com.digitinary.DStore.model.mapper;

import com.digitinary.DStore.repository.entity.Category;
import com.digitinary.DStore.model.request.CreateCategoryRequest;
import com.digitinary.DStore.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Category toCategory(CreateCategoryRequest request);

    public abstract CategoryResponse toView(Category category);
}
