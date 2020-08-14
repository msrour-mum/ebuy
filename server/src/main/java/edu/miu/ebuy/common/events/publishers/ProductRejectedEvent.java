package edu.miu.ebuy.common.events.publishers;

import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.User;

public class ProductRejectedEvent {

    private User vendor;
    public User getVendor() {
        return vendor;
    }

    private Product product;
    public Product getProduct() {
        return product;
    }

    public ProductRejectedEvent(User vendor, Product product) {
        this.vendor = vendor;
        this.product = product;
    }
}
