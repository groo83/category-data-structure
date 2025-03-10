package org.example.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryTree {
    private final Map<Integer, Category> categoryMap = new HashMap<>();
    private final Map<Integer, List<Integer>> relationMap = new HashMap<>();

    public void add(int parentIdx, Category category) {
        add(parentIdx, category.id, category.name);
    }

    public void add(int parentIdx, int categoryId, String childName) {
        Category child = categoryMap.computeIfAbsent(categoryId, id -> new Category(id, childName));
        // 부모가 있다면
        if (parentIdx != -1) {
            relationMap.computeIfAbsent(parentIdx, a -> new ArrayList<>())
                    .add(child.id);
        }
    }

    public Category getCategory(int categoryId) {
        if (!categoryMap.containsKey(categoryId)) {
            return null;
        }
        return makeCategoryTree(categoryId);
    }

    private Category makeCategoryTree(int categoryId) {
        Category category = categoryMap.get(categoryId);
        Category resultCategory = new Category(category.id, category.name);

        if (relationMap.containsKey(categoryId)) {
            for (int id : relationMap.get(categoryId)) {
                resultCategory.children.add(makeCategoryTree(id));
            }
        }
        return resultCategory;
    }

    public String toJSON(int categoryId) throws JsonProcessingException {
        Category category = getCategory(categoryId);
        if (category == null) {
            return "{}";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(category);
    }

}
