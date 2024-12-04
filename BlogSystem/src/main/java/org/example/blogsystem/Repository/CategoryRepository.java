package org.example.blogsystem.Repository;

import org.example.blogsystem.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryByCategoryId (Integer id);
}
