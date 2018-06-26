package pl.put.swolarz.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = AccountRegistration.TABLE_NAME)
public class AccountRegistration {

    public static final String TABLE_NAME = "ACCOUNT_REGISTRATION";

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "VALID_TO", nullable = false)
    private Date validTo;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @OneToOne(cascade = CascadeType.PERSIST)
    @Column(name = "USER_ACCOUNT_ID", nullable = false)
    private UserAccount account;
}
