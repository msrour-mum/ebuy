package edu.miu.ebuy.common.events.publishers;

import edu.miu.ebuy.models.User;

public class SignupSuccessfullyEvent {

    private User user;
    public User getUser() {
        return user;
    }

    public SignupSuccessfullyEvent(User user) {
        this.user = user;
    }
}
