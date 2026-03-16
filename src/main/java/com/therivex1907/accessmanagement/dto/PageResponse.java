package com.therivex1907.accessmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PageResponse<T> {
    private List<T> data;
    private Integer page;
    private Integer size;
    private Integer totalItems;
    private Integer totalPages;
}
