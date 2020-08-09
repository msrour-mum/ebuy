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
public class OrderReport {
     @Id
    private long id;
    private Date orderDate;
    private int userId;
    private String userName;
    private String userAddress;
    private double shipping;
    private double total;
}
