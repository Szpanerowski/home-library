package pl.put.swolarz.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@Table(name = LibraryUser.TABLE_NAME)
public class LibraryUser {

    public static final String TABLE_NAME = "LIBRARY_USER";

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false, length = 128)
    private String name;
}
