package org.example.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Category {
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("children")
    List<Category> children;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
    }
}
