package pl.put.swolarz.application.service.account;


import pl.put.swolarz.application.exception.ApplicationException;

public interface RegistrationService {

    void registerUser(String username, String email, String password);
    void confirmRegistration(String registrationToken) throws ApplicationException;
}
