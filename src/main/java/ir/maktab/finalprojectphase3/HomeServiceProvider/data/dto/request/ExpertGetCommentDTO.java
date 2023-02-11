package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Comment;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;

import java.util.ArrayList;
import java.util.List;

public class ExpertRequestDTO {
    ExpertStatus expertStatus;
    byte[] personalPhoto;
    List<SubService> subServices = new ArrayList<>();
    double rate;
    List<Comment> comments = new ArrayList<>();
    boolean isDeleted;
}
