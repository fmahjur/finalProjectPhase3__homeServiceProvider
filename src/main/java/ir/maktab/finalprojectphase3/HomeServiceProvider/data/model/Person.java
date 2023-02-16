package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class Person extends BaseEntity {
    String firstname;

    String lastname;

    @Column(unique = true)
    @Email
    String email;

    public Person(Long id, String firstname, String lastname, String email) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
