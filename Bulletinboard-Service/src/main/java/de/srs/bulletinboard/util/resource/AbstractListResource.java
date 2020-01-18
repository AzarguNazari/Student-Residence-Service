package de.srs.bulletinboard.util.resource;

import org.springframework.hateoas.ResourceSupport;

public abstract class AbstractListResource extends ResourceSupport{

	private Integer pageNumber;

	private Integer totalPages;

    private Long totalElements;

   
    public AbstractListResource(Integer pageNumber, Integer totalPages, Long totalElements) {
        this.pageNumber = pageNumber;
        
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }
}
