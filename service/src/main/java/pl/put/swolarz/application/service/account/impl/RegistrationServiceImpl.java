package pl.put.swolarz.application.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import pl.put.swolarz.application.common.exception.ApplicationError;
import pl.put.swolarz.application.common.exception.ApplicationException;
import pl.put.swolarz.application.common.helper.authentication.AuthenticationHelper;
import pl.put.swolarz.infrastructure.manager.session.UserSessionManager;
import pl.put.swolarz.application.common.provider.email.EmailContentProvider;
import pl.put.swolarz.application.common.provider.email.EmailTemplate;
import pl.put.swolarz.application.service.account.RegistrationService;
import pl.put.swolarz.infrastructure.client.mail.EmailContent;
import pl.put.swolarz.infrastructure.client.mail.MailServiceClient;
import pl.put.swolarz.application.common.util.TokenGenerator;
import pl.put.swolarz.domain.entity.user.AccountRegistration;
import pl.put.swolarz.domain.entity.user.LibraryUser;
import pl.put.swolarz.domain.entity.user.UserAccount;
import pl.put.swolarz.domain.entity.user.UserAccountStatus;
import pl.put.swolarz.infrastructure.respository.user.AccountRegistrationRepository;
import pl.put.swolarz.infrastructure.respository.user.UserAccountRepository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static pl.put.swolarz.application.common.provider.email.property.RegistrationConfirmationTemplateProperties.*;


public class RegistrationServiceImpl implements RegistrationService {

    private static final int REGISTRATION_TOKEN_LENGTH = 128;

    @Value("${application.domain.url}")
    private String domainUrl;

    @Autowired
    private AccountRegistrationRepository accountRegistrationRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private MailServiceClient mailServiceClient;
    @Autowired
    private AuthenticationHelper authenticationHelper;
    @Autowired
    private EmailContentProvider emailContentProvider;
    @Autowired
    private UserSessionManager userSessionManager;


    @Override
    @Transactional
    public void registerUser(String username, String email, String password) {

        String passwordHash = authenticationHelper.hashPassword(password);

        LibraryUser libraryUser = new LibraryUser(0L, username);
        UserAccount userAccount = new UserAccount(0L, email, passwordHash, libraryUser, UserAccountStatus.PENDING);

        AccountRegistration accountRegistration = createAccountRegistration(userAccount);

        while (accountRegistrationRepository.findByToken(accountRegistration.getToken()) != null) {
            accountRegistration = createAccountRegistration(userAccount);
        }

        accountRegistrationRepository.save(accountRegistration);
        sendEmailInvitation(accountRegistration);
    }

    @Override
    @Transactional
    public void confirmRegistration(String registrationToken) throws ApplicationException {

        AccountRegistration accountRegistration = accountRegistrationRepository.findByToken(registrationToken);

        if (accountRegistration == null) {
            throw new ApplicationException(ApplicationError.REGISTRATION_NOT_FOUND);
        }

        UserAccount userAccount = accountRegistration.getAccount();
        userAccount.confirmAccount();

        userAccountRepository.save(userAccount);
        accountRegistrationRepository.delete(accountRegistration);
    }

    private AccountRegistration createAccountRegistration(UserAccount userAccount) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        TokenGenerator tokenGenerator = new TokenGenerator(REGISTRATION_TOKEN_LENGTH);
        String registrationToken = tokenGenerator.generate();

        return new AccountRegistration(0L, calendar.getTime(), registrationToken, userAccount);
    }

    private void sendEmailInvitation(AccountRegistration accountRegistration) {

        UserAccount account = accountRegistration.getAccount();

        EmailContent emailContent = emailContentProvider.loadEmailContent(
                EmailTemplate.REGISTRATION_CONFIRMATION,
                userSessionManager.getCurrentLocale(null),
                prepareEmailProperties(accountRegistration)
        );

        mailServiceClient.sendEmail(account.getEmail(), emailContent);
    }

    private Map<String, String> prepareEmailProperties(AccountRegistration accountRegistration) {

        UserAccount account = accountRegistration.getAccount();
        Map<String, String> properties = new HashMap<>();

        properties.put(USERNAME_PROPERTY, account.getUser().getName());
        properties.put(DOMAIN_PROPERTY, domainUrl);
        properties.put(TOKEN_PROPERTY, accountRegistration.getToken());

        return properties;
    }
}
