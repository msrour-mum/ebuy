package edu.miu.ebuy.models;

import edu.miu.ebuy.models.lookup.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private User user;



    @Enumerated(EnumType.ORDINAL)
    @Column(name = "statusId", nullable = false)
    private OrderStatus status;

    @Column(name ="creationDate", nullable = false, columnDefinition = "DATETIME default now()")
    private Date creationDate;

    @Column(name ="total" , nullable = false)
    private double total;

    @Column(name ="shipping" , nullable = false)
    private double shipping;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="order_id", nullable = false)
    List<OrderItems> items =new ArrayList<>();



    public void addComment(OrderItems item)    { if (items!=null)    items.add(item);    }
    public void removeComment(OrderItems item)
    {
        items.remove(item);
    }
}
