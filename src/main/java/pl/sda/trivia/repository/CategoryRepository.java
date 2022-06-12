package pl.sda.trivia.repository;

import pl.sda.trivia.api.CategoryItem;

import java.util.List;

public interface CategoryRepository {
    List<CategoryItem> findAll();
}
