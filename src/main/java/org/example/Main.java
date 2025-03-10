package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.category.Category;
import org.example.category.CategoryTree;

public class Main {
    public static void main(String[] args) {
        CategoryTree categoryTree = new CategoryTree();
        Category AnonymousBord = new Category(6, "익명게시판");
        categoryTree.add(-1, 1, "남자");
        categoryTree.add(1, 2, "엑소");
        categoryTree.add(2, 3, "공지사항");
        categoryTree.add(1, 4, "방탄소년단");
        categoryTree.add(4, 5, "공지사항");
        categoryTree.add(4, AnonymousBord);
        categoryTree.add(4, 7, "뷔");
        categoryTree.add(-1, 8, "여자");
        categoryTree.add(8, 9, "블랙핑크");
        categoryTree.add(9, 10, "공지사항");
        categoryTree.add(9, AnonymousBord);
        categoryTree.add(9, 11, "로제");

        try {
            // 검색으로 json string get
            System.out.println(categoryTree.toJSON(1));
            System.out.println("================================");
            System.out.println(categoryTree.toJSON(8));
            System.out.println("================================");
            System.out.println(categoryTree.toJSON(4));
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}