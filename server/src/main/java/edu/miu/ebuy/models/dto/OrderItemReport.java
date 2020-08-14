package edu.miu.ebuy.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Immutable
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemReport {
    @Id
    private long id;
    private int userId;
    private Date orderDate;
    private String productName;
    private double price;
    private int quantity;
    private double shipping;
    private double itemTotal;
}
