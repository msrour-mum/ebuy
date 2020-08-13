package edu.miu.ebuy.models;

import com.sun.istack.Nullable;
import edu.miu.ebuy.models.lookup.CardType;
import edu.miu.ebuy.models.lookup.ProductStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,length = 1000)
    private String shortDescription;

    @Column(nullable = false,length = 1000)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name ="vendorId")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name ="categoryId")
    private Category category;

    @Column(name ="cost" , nullable = false)
    private double cost;

    @Column(name ="price" , nullable = false)
    private double price;

    @Transient
    private double promotionPrice;

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public double getPromotionPrice() {

        LocalDate currentDate = LocalDate.now();

        Optional<Promotion> promotion = getPromotions()
                .stream()
                .filter(p-> (p.getFromDate().isBefore(currentDate) || p.getFromDate().isEqual(currentDate))&&
                        (p.getToDate().isAfter(currentDate)  || p.getToDate().isEqual(currentDate)))
                .findFirst();
        promotion.ifPresent(activePromotion -> this.promotionPrice = getPrice() - (activePromotion.getDiscount() / 100 * getPrice()));
        return this.promotionPrice;
    }


    @ManyToOne(optional = false)
    @JoinColumn(name ="statusId")
    private ProductStatus productStatus;

    @Column(name = "isPublished",nullable = false, columnDefinition = "BIT(1) default 1")
    private boolean isPublished;

    @Column(name = "isService",nullable = false, columnDefinition = "BIT(1) default 0")
    private boolean isService;

    private String imageUrl;


    @Column(name = "isDeleted",nullable = false, columnDefinition = "BIT(1) default 0")
    private boolean isDeleted;

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Promotion> promotions =new ArrayList<>();

    public Product(Integer id) {
        this.id = id;
    }

    public Product(String name, String shortDescription, String description, User user, Category category, double cost, double price, ProductStatus productStatus, boolean isPublished, boolean isService, String imageUrl) {

        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.user = user;
        this.category = category;
        this.cost = cost;
        this.price = price;
        this.productStatus = productStatus;
        this.isPublished = isPublished;
        this.isService = isService;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Product setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Product setUser(User user) {
        this.user = user;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Product setCategory(Category category) {
        this.category = category;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Product setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public Product setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
        return this;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public Product setPublished(boolean published) {
        isPublished = published;
        return this;
    }

    public boolean isService() {
        return isService;
    }

    public Product setService(boolean service) {
        isService = service;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Product setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }


}
