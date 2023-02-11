package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseServiceRequestDTO {
    String name;
}
