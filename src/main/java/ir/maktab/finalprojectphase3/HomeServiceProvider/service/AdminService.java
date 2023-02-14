package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.ExpertUpdateDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.SubServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.CustomerResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;

import java.util.List;

public interface AdminService {
    void addNewService(BaseServiceRequestDTO baseService);

    void addNewSubService(SubServiceRequestDTO subService);

    void confirmExpert(ExpertUpdateDTO expertUpdateDTO);

    void allocationServiceToExpert(SubServiceRequestDTO subServiceRequestDTO, UserEmailDTO expertEmailDTO);

    void removeExpertFromService(ExpertUpdateDTO expertUpdateDTO, SubServiceRequestDTO subServiceRequestDTO);

    List<BaseServiceResponseDTO> showAllService();

    List<SubServiceResponseDTO> showSubServices(BaseServiceRequestDTO service);

    List<CustomerResponseDTO> showAllCustomer();

    List<ExpertResponseDTO> showAllExpert();

    List<ExpertResponseDTO> showAllNewExpert();

    List<ExpertResponseDTO> showAllConfirmedExpert();
}
