package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDTO {
    String comment;
    double score;
    ExpertRequestDTO expertRequestDTO;
}
