package edu.miu.ebuy.common.events.listeners;

import edu.miu.ebuy.common.email.IEmailService;
import edu.miu.ebuy.common.events.publishers.ProductApprovedEvent;
import edu.miu.ebuy.common.events.publishers.SignupSuccessfullyEvent;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProductApprovedListener {


    @Autowired
    IEmailService emailService;

    @Value("${email.template.product.approved.title}")
    String emailTitle;
    @Value("${email.template.product.approved.body}")
    String emailBody;

    @Async
    @EventListener
    public void handleProductApprovedEvent(ProductApprovedEvent e) {

        emailBody = emailBody.replace("##USER_NAME##", e.getVendor().getName());
        emailBody = emailBody.replace("##PRODUCT_ID##", Integer.toString(e.getProduct().getId()));
        emailService.sendMail(emailTitle, emailBody, e.getVendor().getEmail());

    }
}
