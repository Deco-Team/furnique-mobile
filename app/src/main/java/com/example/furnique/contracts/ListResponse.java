package com.example.furnique.contracts;

import java.util.List;

public class ListResponse<T> {
    public ListResponse(List<T> docs) {
        this.docs = docs;
    }

    public List<T> getDocs() {
        return docs;
    }

    List<T> docs;
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
