package pl.put.swolarz.infrastructure.client.mail.impl;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.springsupport.SimpleJavaMailSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import pl.put.swolarz.infrastructure.client.mail.MailServiceClient;

@Component
@Import(SimpleJavaMailSpringSupport.class)
public class MailServiceClientImpl implements MailServiceClient {

    private static final String FROM_ADDRESS = "root@localhost";

    @Autowired
    private Mailer mailer;

    @Override
    public void sendEmail(String toAddress, String subject, String htmlContent) {

        Email email = EmailBuilder.startingBlank()
                .withHTMLText(htmlContent)
                .withSubject(subject)
                .from(FROM_ADDRESS)
                .to(toAddress)
                .buildEmail();

        mailer.sendMail(email);
    }

    //@PostConstruct
    /**
     * Test method
     * Todo remove after testing
     */
    /*public void init() {

        Email testEmail = EmailBuilder.startingBlank()
                .appendTextHTML("<b><i>\"Consider this an invitation to my gangsta nation...\"</i></b>")
                .withSubject("Test")
                .from("root@localhost")
                .to("szymon.wol@vp.pl")
                .buildEmail();

        Email testStudentEmail = EmailBuilder.startingBlank()
                .appendTextHTML("<b><i>\"Consider this an invitation to my gangsta nation...\"</i></b>")
                .withSubject("Test")
                .from("root@localhost")
                .to("szymon.wolarz@student.put.poznan.pl")
                .buildEmail();

        mailer.sendMail(testEmail);
        mailer.sendMail(testStudentEmail);
    }*/
}
