package pl.put.swolarz.infrastructure.client.mail;

public interface MailServiceClient {

    void sendEmail(String emailTo, EmailContent emailContent);
}
