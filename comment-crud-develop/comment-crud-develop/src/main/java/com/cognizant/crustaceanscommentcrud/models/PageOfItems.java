package com.cognizant.crustaceanscommentcrud.models;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageOfItems<T> {
    private List<T> comments;
    private long totalElements;
    private boolean last;

    public PageOfItems(PageOfItems<T> page){
        this.last=page.last;
        this.comments=page.comments;
        this.totalElements=page.totalElements;
    }

    public PageOfItems(List<T> comments, long totalElements, boolean last) {
        this.comments = comments;
        this.totalElements = totalElements;
        this.last = last;
    }

    public PageOfItems(Page<T> page) {
        this(page.getContent(),page.getTotalElements(),!page.hasNext());
    }

    public List<T> getComments() {
        return comments;
    }

    public void setComments(List<T> comments) {
        this.comments = comments;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    
    
}
