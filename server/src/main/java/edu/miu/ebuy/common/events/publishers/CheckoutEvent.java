package edu.miu.ebuy.common.events.publishers;

import edu.miu.ebuy.models.User;

public class CheckoutEvent {

    private User user;
    public User getUser() {
        return user;
    }

    public CheckoutEvent(User user) {
        this.user = user;
    }
}
