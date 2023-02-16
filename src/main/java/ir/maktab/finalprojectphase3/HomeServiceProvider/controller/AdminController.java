package ir.maktab.finalprojectphase3.HomeServiceProvider.controller;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/add-base-service")
    public void addMainService(@RequestBody BaseServiceRequestDTO baseServiceRequestDTO) {
        adminService.addNewService(baseServiceRequestDTO);
    }

    @DeleteMapping("/delete-base-service/{id}")
    public void deleteMainService(@PathVariable Long id) {
        adminService.removeService(id);
    }

    @PostMapping("/add-sub-service")
    public void addSubService(@RequestBody SubServiceRequestDTO subServiceRequestDTO) {
        adminService.addNewSubService(subServiceRequestDTO);
    }

    @PostMapping("/allocation-service-to-expert/{subServiceId}/{expertId}")
    public void allocationServiceToExpert(@PathVariable Long subServiceId, @PathVariable Long expertId) {
        adminService.allocationServiceToExpert(subServiceId, expertId);
    }

    @DeleteMapping("/remove-service-from-expert/{subServiceId}/{expertId}")
    public void deleteServiceFromExpert(@PathVariable Long subServiceId, @PathVariable Long expertId) {
        adminService.removeExpertFromService(subServiceId, expertId);
    }

    @GetMapping("/show-all-services")
    public List<BaseServiceResponseDTO> findAllBaseService() {
        return adminService.showAllService();
    }

    @GetMapping("/show-all-sub-services")
    public List<SubServiceResponseDTO> findAllSubService() {
        return adminService.showSubServices();
    }

    @GetMapping("/show-all-sub-services-by-service/{baseServiceId}")
    public List<SubServiceResponseDTO> findAllSubServiceByService(@PathVariable Long baseServiceId) {
        return adminService.showSubServicesByService(baseServiceId);
    }

    @PutMapping("/edit-sub-service")
    public void editSubServiceBasePriceAndDescription(@RequestBody UpdateSubServiceDTO updateSubServiceDTO) {
        adminService.editSubService(updateSubServiceDTO);
    }

    @GetMapping("/show-all-expert")
    public List<ExpertResponseDTO> findAllExpert() {
        return adminService.showAllExpert();
    }

    @PutMapping("/confirm-expert/{expertId}")
    public void confirmExpert(@PathVariable Long expertId) {
        adminService.confirmExpert(expertId);
    }

    @PutMapping("/check-expert-delay/{offerId}")
    public void checkExpertDelayForDoingWork(@PathVariable Long offerId) {
        adminService.checkExpertDelayForWork(offerId);
    }

    @PutMapping("/change-expert-activation")
    public void inactiveExpertAccount(@PathVariable Long expertId) {
        adminService.deActiveExpert(expertId);
    }

    @PostMapping("/filter-experts")
    public List<FilterExpertResponseDTO> expertsFilter(@RequestBody FilterExpertDTO expertDTO) {
        return adminService.expertFilter(expertDTO);
    }

    @PostMapping("/filter-customers")
    public List<FilterCustomerResponseDTO> customersFilter(@RequestBody FilterCustomerDTO customerDTO) {
        return adminService.customerFilter(customerDTO);
    }

    @Transactional
    @GetMapping("/show-sub-services-expert/{subServiceId}")
    public List<ExpertResponseDTO> viewSubServiceExperts(@PathVariable Long subServiceId) {
        return adminService.showSubServicesExpert(subServiceId);
    }
}
