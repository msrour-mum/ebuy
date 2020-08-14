package edu.miu.ebuy.common.email;

public interface IEmailService {

    void sendMail(String subject, String body, String to);
    void sendMail(String subject, String body, String... to);
}
