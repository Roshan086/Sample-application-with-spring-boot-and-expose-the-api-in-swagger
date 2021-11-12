package com.sample.utill;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@ApiModel(value = "Page DTO")
public class PaginationUtil {

    @NotNull(message = "Current Page can not be Null")
    @NotBlank(message = "Current page can not be blank")
    @ApiModelProperty(value = "Current page", notes = "Current page can not be Null", required = true)
    private Integer currentPage;

    @NotNull(message = "Items per Pages can not be Null")
    @NotBlank(message = "Items per Pages can not be blank")
    @ApiModelProperty(value = "Items per Pages", notes = "Items per Pages can not be Null", required = true)
    private Integer itemsPerPages;

    @NotNull(message = "Sort By can not be Null")
    @NotBlank(message = "Sort By can not be blank")
    @ApiModelProperty(value = "Sort By", notes = "Sort By can not be Null")
    private String sortBy;

    @NotNull(message = "Direction can not be Null")
    @NotBlank(message = "Direction can not be blank")
    @ApiModelProperty(value = "Direction", notes = "Direction can not be Null")
    private String direction;

    /*@NotNull(message = "Controller Name can not be Null")
    @NotBlank(message = "Controller Name can not be blank")
    @ApiModelProperty(value = "Controller Name", notes = "Controller Name can not be Null")
    private String controllerName;*/


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getItemsPerPages() {
        return itemsPerPages;
    }

    public void setItemsPerPages(Integer itemsPerPages) {
        this.itemsPerPages = itemsPerPages;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

   /* public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }*/
}
