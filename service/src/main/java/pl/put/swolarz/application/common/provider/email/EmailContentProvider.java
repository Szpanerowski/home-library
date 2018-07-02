package pl.put.swolarz.application.common.provider.email;

import org.springframework.stereotype.Component;
import pl.put.swolarz.infrastructure.client.mail.EmailContent;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;

@Component
public class EmailContentProvider {

    private static final String PROPERTY_ESCAPE = "${";
    private static final String PROPERTY_END = "}";
    private static final String SUBJECT_KEY = "$subject";


    public EmailContent loadEmailContent(EmailTemplate emailTemplate, Locale locale, Map<String, String> properties) {
        URL templateUrl = getClass().getClassLoader().getResource(emailTemplate.getTemplatePath());
        String template = "";

        try {
            template = new String(Files.readAllBytes(Paths.get(templateUrl.toURI())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String content = getReplacedContent(template, properties);

        return prepareEmailContent(content);
    }

    private EmailContent prepareEmailContent(String content) {

        if (!content.startsWith(SUBJECT_KEY))
            return new EmailContent("", content);

        String[] contentWithSubject = content.split(System.lineSeparator(), 2);

        return new EmailContent(contentWithSubject[0], contentWithSubject[1]);
    }

    private String getReplacedContent(String template, Map<String, String> properties) {

        StringBuilder content = new StringBuilder(template.length() * 2);
        int index = 0;
        int prevIndex = 0;

        while ((index = template.indexOf(PROPERTY_ESCAPE, index)) >= 0) {

            content.append(template.substring(prevIndex, index));

            int endIndex = template.indexOf(PROPERTY_END, index);
            String property = template.substring(index + 2, endIndex);

            if (properties.containsKey(property))
                content.append(properties.get(property));
            else
                content.append(template.substring(index, endIndex));

            prevIndex = index = endIndex + 1;
        }

        content.append(template.substring(prevIndex));

        return content.toString();
    }
}
