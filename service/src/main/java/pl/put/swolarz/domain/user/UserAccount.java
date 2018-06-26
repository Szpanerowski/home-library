package pl.put.swolarz.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@Entity
@Table(name = UserAccount.TABLE_NAME)
public class UserAccount {

    public static final String TABLE_NAME = "USER_ACCOUNT";

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    private LibraryUser user;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 16)
    private UserAccountStatus status;
}
