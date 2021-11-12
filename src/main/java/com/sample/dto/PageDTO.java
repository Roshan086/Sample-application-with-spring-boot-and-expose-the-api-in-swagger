package com.sample.dto;



import java.util.List;


public class PageDTO {
    private Long totalElements;
    private Integer totalPages;
    private List data;

    public PageDTO() {
    }

    public PageDTO(List data, Long totalElements, Integer totalPages) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.data = data;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
