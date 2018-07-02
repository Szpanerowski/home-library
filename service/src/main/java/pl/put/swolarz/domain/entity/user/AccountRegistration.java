package pl.put.swolarz.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = AccountRegistration.TABLE_NAME)
public class AccountRegistration {

    public static final String TABLE_NAME = "ACCOUNT_REGISTRATION";
    private static final String SEQUENCE_NAME = "ACCOUNT_REGISTRATION_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Column(name = "ID")
    private long id;

    @Column(name = "REGISTERED", nullable = false)
    private Date registered;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ACCOUNT_ID", nullable = false, unique = true)
    private UserAccount account;
}
