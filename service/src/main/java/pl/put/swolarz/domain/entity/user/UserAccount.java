package pl.put.swolarz.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity
@Table(name = UserAccount.TABLE_NAME)
public class UserAccount {

    public static final String TABLE_NAME = "USER_ACCOUNT";
    private static final String SEQUENCE_NAME = "USER_ACCOUNT_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    private LibraryUser user;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 16)
    private UserAccountStatus status;


    public void confirmAccount() {
        this.setStatus(UserAccountStatus.CONFIRMED);
    }
}
