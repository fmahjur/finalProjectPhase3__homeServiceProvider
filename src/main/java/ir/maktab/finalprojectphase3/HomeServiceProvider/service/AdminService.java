package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.*;

import java.util.List;

public interface AdminService {
    void addNewService(BaseServiceRequestDTO baseService);

    void removeService(Long baseServiceId);

    void addNewSubService(SubServiceRequestDTO subService);

    void editSubService(UpdateSubServiceDTO subService);

    void confirmExpert(Long expertId);

    void allocationServiceToExpert(Long subServiceId, Long expertId);

    void removeExpertFromService(Long subServiceId, Long expertId);

    List<BaseServiceResponseDTO> showAllService();

    List<SubServiceResponseDTO> showSubServices();

    List<SubServiceResponseDTO> showSubServicesByService(Long baseServiceRequestId);

    List<CustomerResponseDTO> showAllCustomer();

    List<ExpertResponseDTO> showAllExpert();

    List<ExpertResponseDTO> showAllNewExpert();

    List<ExpertResponseDTO> showAllConfirmedExpert();

    void checkExpertDelayForWork(Long offerId);

    void deActiveExpert(Long expertId);

    List<FilterExpertResponseDTO> expertFilter(FilterExpertDTO expertDTO);

    List<FilterCustomerResponseDTO> customerFilter(FilterCustomerDTO customerDTO);

    List<ExpertResponseDTO> showSubServicesExpert(Long subServiceId);
}
