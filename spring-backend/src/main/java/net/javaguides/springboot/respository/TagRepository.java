package net.javaguides.springboot.respository;

import net.javaguides.springboot.model.Ingredient;
import net.javaguides.springboot.model.Tag;
import org.hibernate.hql.spi.id.local.LocalTemporaryTableBulkIdStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "SELECT * from public.tags inner join public.recipe_tags on recipe_tags.id_tags = tags.id where recipe_tags.id_recipe = ?1", nativeQuery = true)
    List<Tag> getTagsByRecipe(Long id);

    @Query(value = "insert into public.recipe_tags(id_recipe, id_tags) VALUES (?1, ?2) RETURNING true", nativeQuery = true)
    boolean saveRecipeTags(long id, long tags);

}
