package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.ExpertUpdateDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.SubServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.CustomerResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.ExpertMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.SubServiceMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.AdminService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.ExpertValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final BaseServiceServiceImpl baseServiceService;
    private final SubServiceServiceImpl subServiceService;
    private final ExpertServiceImpl expertService;
    private final CustomerServiceImpl customerService;

    @Override
    public void addNewService(BaseServiceRequestDTO baseService) {
        baseServiceService.add(baseService);
    }

    @Override
    public void addNewSubService(SubServiceRequestDTO subService) {
        subServiceService.add(subService);
    }

    @Override
    public void confirmExpert(ExpertUpdateDTO expertUpdateDTO) {
        Expert expert = ExpertMapper.INSTANCE.updateDtoToModel(expertUpdateDTO);
        expert.setExpertStatus(ExpertStatus.ACCEPTED);
        expertService.update(expert);
    }

    @Override
    public void allocationServiceToExpert(SubServiceRequestDTO subServiceRequestDTO, UserEmailDTO expertEmailDTO) {
        ExpertValidator.isExpertConfirmed(expertEmailDTO);
        expertService.updateExpertSubService(subServiceRequestDTO, expertEmailDTO);
    }

    @Override
    public void removeExpertFromService(ExpertUpdateDTO expertUpdateDTO, SubServiceRequestDTO subServiceRequestDTO) {
        Expert expert = ExpertMapper.INSTANCE.updateDtoToModel(expertUpdateDTO);
        SubService subService = SubServiceMapper.INSTANCE.requestDtoToModel(subServiceRequestDTO);
        ExpertValidator.hasASubService(expert, subService);
        expert.getSubServices().remove(subService);
        expertService.update(expert);
    }

    @Override
    public List<BaseServiceResponseDTO> showAllService() {
        return baseServiceService.selectAll();
    }

    @Override
    public List<SubServiceResponseDTO> showSubServices(BaseServiceRequestDTO service) {
        return subServiceService.getSubServicesByService(service);
    }

    @Override
    public List<CustomerResponseDTO> showAllCustomer() {
        return customerService.selectAll();
    }

    @Override
    public List<ExpertResponseDTO> showAllExpert() {
        return expertService.selectAll();
    }

    @Override
    public List<ExpertResponseDTO> showAllNewExpert() {
        return expertService.selectExpertByExpertStatus(ExpertStatus.NEW);
    }

    @Override
    public List<ExpertResponseDTO> showAllConfirmedExpert() {
        return expertService.selectExpertByExpertStatus(ExpertStatus.ACCEPTED);
    }
}
