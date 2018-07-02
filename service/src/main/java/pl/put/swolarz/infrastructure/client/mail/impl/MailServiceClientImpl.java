package pl.put.swolarz.infrastructure.client.mail.impl;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.springsupport.SimpleJavaMailSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import pl.put.swolarz.infrastructure.client.mail.EmailContent;
import pl.put.swolarz.infrastructure.client.mail.MailServiceClient;

@Component
@Import(SimpleJavaMailSpringSupport.class)
public class MailServiceClientImpl implements MailServiceClient {

    private static final String FROM_ADDRESS = "root@localhost";

    @Autowired
    private Mailer mailer;

    @Override
    public void sendEmail(String toAddress, EmailContent emailContent) {

        Email email = EmailBuilder.startingBlank()
                .withHTMLText(emailContent.getHtmlContent())
                .withSubject(emailContent.getSubject())
                .from(FROM_ADDRESS)
                .to(toAddress)
                .buildEmail();

        mailer.sendMail(email, true);
    }
}
