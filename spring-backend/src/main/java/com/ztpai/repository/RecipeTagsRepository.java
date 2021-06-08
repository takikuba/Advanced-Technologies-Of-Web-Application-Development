package com.ztpai.repository;

import com.ztpai.model.RecipeTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeTagsRepository extends JpaRepository<RecipeTags, Long> {
}
