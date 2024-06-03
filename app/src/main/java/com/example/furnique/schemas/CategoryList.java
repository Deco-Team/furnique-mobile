package com.example.furnique.schemas;

import com.example.furnique.contracts.Constants;

import java.util.List;

public class CategoryList {
    public CategoryList(List<Category> docs) {
        this.docs = docs;
    }

    public List<Category> getDocs() {
        return docs;
    }

    List<Category> docs;
//    Integer totalDocs;
//    Integer offset;
//    Integer limit;
//    Integer totalPages;
//    Integer page;
//    Integer pagingCounter;
//    Boolean hasPrevPage;
//    Boolean hasNextPage;
//    Integer prevPage;
//    Integer nextPage;
}
