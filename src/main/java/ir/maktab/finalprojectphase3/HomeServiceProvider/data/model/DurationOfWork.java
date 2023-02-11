package ir.maktab.finalprojectphase3.HomeServiceProvider.data.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DurationOfWork implements Serializable {
    int days;
    int hours;
    int minute;

    @Override
    public String toString() {
        return days + " days, " +
                hours + " hours, " +
                minute + " minutes";
    }
}
