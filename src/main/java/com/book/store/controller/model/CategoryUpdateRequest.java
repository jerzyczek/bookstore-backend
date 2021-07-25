package com.book.store.controller.model;

import lombok.Data;

@Data
public class CategoryUpdateRequest {
    private String oldName;
    private String newName;
}
