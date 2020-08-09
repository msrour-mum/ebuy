package edu.miu.ebuy.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Immutable
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProfitDto {
    @Id
    private Integer productId;
    private String productName;
    private Integer vendorId;
    private String vendorName;
    private String categoryName;
    private double cost;
    private double price;




}
