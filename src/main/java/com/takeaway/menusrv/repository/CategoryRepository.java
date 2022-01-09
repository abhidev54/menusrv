package com.takeaway.menusrv.repository;

import com.takeaway.menusrv.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "categories")
public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
    @RestResource(rel = "by-name", description = @Description("Find by Category name"))
    Category findFirstByName(@Param("name") String name);
}
