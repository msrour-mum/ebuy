package edu.miu.ebuy.common.events.listeners;

import edu.miu.ebuy.common.email.IEmailService;
import edu.miu.ebuy.common.events.publishers.CheckoutEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener {


    @Autowired
    IEmailService emailService;

    @Value("${email.template.checkout.title}")
    String emailTitle;
    @Value("${email.template.checkout.body}")
    String emailBody;

    @Async
    @EventListener
    public void handleCheckoutEvent(CheckoutEvent e) {

        emailBody = emailBody.replace("##USER_NAME##", e.getUser().getName());
        emailBody = emailBody.replace("##USER_ID##", Long.toString(e.getUser().getId()));
        emailService.sendMail(emailTitle, emailBody,e.getUser().getEmail());

    }
}
