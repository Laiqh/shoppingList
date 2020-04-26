package com.javaguru.shoppinglist.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

public class ProductDTO {

    @Null(message = "Please do not provide ID", groups = {Create.class})
    @NotNull(message = "ID must not be null", groups = {Update.class})
    private Long id;

    private String name;

    private String category;

    @NotNull(message = "Please provide a price", groups = {Create.class})
    private BigDecimal price;

    private BigDecimal discount;

    @Null(message = "Please do not provide final price", groups = {Create.class})
    private BigDecimal finalPrice;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public interface Create {
    }

    public interface Update {
    }
}
