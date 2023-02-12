package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Comment;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Credit;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertUpdateDTO {
    String firstname;
    String lastname;
    String emailAddress;
    String username;
    String password;
    CreditRequestDTO creditRequestDTO;
    ExpertStatus expertStatus;
    byte[] personalPhoto;
}
