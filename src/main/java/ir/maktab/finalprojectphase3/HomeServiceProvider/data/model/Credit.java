package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Credit extends BaseEntity {
    Long balance;

    public Credit(Long id, Long balance) {
        super(id);
        this.balance = balance;
    }
}
