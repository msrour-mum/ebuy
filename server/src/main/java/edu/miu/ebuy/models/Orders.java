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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name ="userId")
    private User user;

    @Column(name ="orderDate", nullable = false, columnDefinition = "DATETIME")
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


    public Orders(User user, Date orderDate, double total, double shipping) {

        this.user = user;
        this.orderDate = orderDate;
        this.total = total;
        this.shipping = shipping;
    }

    public long getId() {
        return id;
    }

    public Orders setId(long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Orders setUser(User user) {
        this.user = user;
        return this;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Orders setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public Orders setTotal(double total) {
        this.total = total;
        return this;
    }

    public double getShipping() {
        return shipping;
    }

    public Orders setShipping(double shipping) {
        this.shipping = shipping;
        return this;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public Orders setItems(List<OrderItems> items) {
        this.items = items;
        return this;
    }
}
