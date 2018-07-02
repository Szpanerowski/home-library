package pl.put.swolarz.infrastructure.client.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailContent {

    private String subject;
    private String htmlContent;
}
