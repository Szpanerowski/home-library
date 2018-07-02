package pl.put.swolarz.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = LibraryUser.TABLE_NAME)
public class LibraryUser {

    public static final String TABLE_NAME = "LIBRARY_USER";
    private static final String SEQUENCE_NAME = "LIBRARY_USER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false, unique = true, length = 128)
    private String name;
}
