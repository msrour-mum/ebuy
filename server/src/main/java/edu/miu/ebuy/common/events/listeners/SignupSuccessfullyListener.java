package edu.miu.ebuy.common.events.listeners;

import edu.miu.ebuy.common.email.IEmailService;
import edu.miu.ebuy.common.events.publishers.CheckoutEvent;
import edu.miu.ebuy.common.events.publishers.SignupSuccessfullyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SignupSuccessfullyListener {


    @Autowired
    IEmailService emailService;

    @Value("${email.template.signup.successfully.title}")
    String emailTitle;
    @Value("${email.template.signup.successfully.body}")
    String emailBody;

    @Async
    @EventListener
    public void handleSignupSeccessfullyEvent(SignupSuccessfullyEvent e) {

        emailBody = emailBody.replace("##USER_NAME##", e.getUser().getName());
        emailService.sendMail(emailTitle, emailBody, e.getUser().getEmail());

    }
}
