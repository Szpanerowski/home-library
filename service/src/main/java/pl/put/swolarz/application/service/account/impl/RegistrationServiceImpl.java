package pl.put.swolarz.application.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.put.swolarz.application.exception.ApplicationException;
import pl.put.swolarz.application.exception.type.ApplicationExceptionType;
import pl.put.swolarz.application.helper.AccountPasswordHelper;
import pl.put.swolarz.application.service.account.RegistrationService;
import pl.put.swolarz.infrastructure.client.mail.MailServiceClient;
import pl.put.swolarz.application.util.TokenGenerator;
import pl.put.swolarz.domain.user.AccountRegistration;
import pl.put.swolarz.domain.user.LibraryUser;
import pl.put.swolarz.domain.user.UserAccount;
import pl.put.swolarz.domain.user.UserAccountStatus;
import pl.put.swolarz.infrastructure.respository.user.AccountRegistrationRepository;
import pl.put.swolarz.infrastructure.respository.user.UserAccountRepository;

import java.sql.SQLException;
import java.util.Calendar;

public class RegistrationServiceImpl implements RegistrationService {

    private static final int REGISTRATION_TOKEN_LENGTH = 128;

    @Autowired
    private AccountRegistrationRepository accountRegistrationRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private MailServiceClient mailServiceClient;
    @Autowired
    private AccountPasswordHelper accountPasswordHelper;


    @Override
    @Transactional
    public void registerUser(String username, String email, String password) {

        String passwordHash = accountPasswordHelper.hashPassword(password);

        LibraryUser libraryUser = new LibraryUser(0L, username);
        UserAccount userAccount = new UserAccount(0L, email, passwordHash, libraryUser, UserAccountStatus.PENDING);

        AccountRegistration accountRegistration = createAccountRegistration(userAccount);

        // Todo ensure that there is no registation with the same token
        accountRegistration = createAccountRegistration(userAccount);

        sendEmailInvitation(accountRegistration);
    }

    @Override
    @Transactional
    public void confirmRegistration(String registrationToken) throws ApplicationException {

        AccountRegistration accountRegistration = accountRegistrationRepository.findByToken(registrationToken);

        if (accountRegistration == null) {
            // Todo throw error
            throw new ApplicationException(ApplicationExceptionType.REGISTRATION_NOT_FOUND);
        }

        UserAccount userAccount = accountRegistration.getAccount();
        userAccount.setStatus(UserAccountStatus.CONFIRMED);

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

        // Todo create registration email
        mailServiceClient.sendEmail(null, null, null);
    }
}
