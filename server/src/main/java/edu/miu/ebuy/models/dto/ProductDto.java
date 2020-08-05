package edu.miu.ebuy.models.dto;

import edu.miu.ebuy.models.Category;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.models.lookup.ProductStatus;

import javax.persistence.*;

public class ProductDto {


    private Integer id;
    private String name;
    private String shortDescription;
    private String description;
    private int vendorId;
    private int categoryId;
    private double cost;
    private double price;
    private int statusId;
    private boolean isPublished;
    private boolean isService;
    private String imageUrl;
    private boolean isDeleted;


    private String vendorName;
    private String categoryName;

    public ProductDto(Integer id, String name, String shortDescription, String description, int vendorId, int categoryId, double cost, double price, int statusId, boolean isPublished, boolean isService, String imageUrl, boolean isDeleted, String vendorName, String categoryName) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.vendorId = vendorId;
        this.categoryId = categoryId;
        this.cost = cost;
        this.price = price;
        this.statusId = statusId;
        this.isPublished = isPublished;
        this.isService = isService;
        this.imageUrl = imageUrl;
        this.isDeleted = isDeleted;
        this.vendorName = vendorName;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public ProductDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public ProductDto setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getVendorId() {
        return vendorId;
    }

    public ProductDto setVendorId(int vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public ProductDto setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public ProductDto setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStatusId() {
        return statusId;
    }

    public ProductDto setStatusId(int statusId) {
        this.statusId = statusId;
        return this;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public ProductDto setPublished(boolean published) {
        isPublished = published;
        return this;
    }

    public boolean isService() {
        return isService;
    }

    public ProductDto setService(boolean service) {
        isService = service;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public ProductDto setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public String getVendorName() {
        return vendorName;
    }

    public ProductDto setVendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ProductDto setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}
