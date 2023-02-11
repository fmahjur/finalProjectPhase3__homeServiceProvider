package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseServiceResponseDTO {
    String name;
    List<SubServiceResponseDTO> subServiceResponseDTOList = new ArrayList<>();
}
