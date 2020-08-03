package edu.miu.ebuy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(name ="orderDate", nullable = false, columnDefinition = "DATETIME default now()")
    private Date orderDate;

    @Column(name ="total" , nullable = false)
    private double total;

    @Column(name ="shipping" , nullable = false)
    private double shipping;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="order_id", nullable = false)
    List<OrderItems> items =new ArrayList<>();

    public void addItem(OrderItems item)    { if (items!=null)    items.add(item);    }
    public void removeItem(OrderItems item)
    {
        items.remove(item);
    }
}
