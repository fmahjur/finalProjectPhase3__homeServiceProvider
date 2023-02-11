package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
    String emailAddress;

    public Person(Long id, String firstname, String lastname, String emailAddress) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
    }
}
