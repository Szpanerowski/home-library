package pl.put.swolarz.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
@Data
@AllArgsConstructor
public class UserAccount {

    @Id
    private Long id;

    private String email;
    private String passwordHash;

    private LibraryUser user;
}
