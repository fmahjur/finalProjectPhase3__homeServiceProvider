package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.ExpertMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ExpertActivationException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.OrderStatusException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final BaseServiceServiceImpl baseServiceService;
    private final SubServiceServiceImpl subServiceService;
    private final ExpertServiceImpl expertService;
    private final CustomerServiceImpl customerService;
    private final OfferServiceImpl offerService;

    @Override
    public void addNewService(BaseServiceRequestDTO baseService) {
        baseServiceService.add(baseService);
    }

    @Override
    public void removeService(Long baseServiceId) {
        baseServiceService.remove(baseServiceId);
    }

    @Override
    public void addNewSubService(SubServiceRequestDTO subService) {
        subServiceService.add(subService);
    }

    public void editSubService(UpdateSubServiceDTO subService){
        subServiceService.update(subService);
    }

    @Override
    public void confirmExpert(Long expertId) {
        Expert expert = expertService.findById(expertId);
        expert.setExpertStatus(ExpertStatus.ACCEPTED);
        expertService.update(expert);
    }

    @Override
    public void allocationServiceToExpert(Long subServiceId, Long expertId) {
        expertService.addSubServiceToExpert(subServiceId, expertId);
    }

    @Override
    public void removeExpertFromService(Long subServiceRequestId, Long expertId) {
        expertService.removeSubServiceFromExpert(subServiceRequestId, expertId);
    }

    @Override
    public List<BaseServiceResponseDTO> showAllService() {
        return baseServiceService.selectAll();
    }

    @Override
    public List<SubServiceResponseDTO> showSubServices() {
        return subServiceService.selectAllAvailableService();
    }

    @Override
    public List<SubServiceResponseDTO> showSubServicesByService(Long baseServiceRequestId) {
        return subServiceService.getSubServicesByService(baseServiceRequestId);
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

    @Override
    public void checkExpertDelayForWork(Long offerId) {
        Offer offer = offerService.findById(offerId);
        Orders order = offer.getOrders();
        if (!order.getOrderStatus().equals(OrderStatus.PAID))
            throw new OrderStatusException("this order is not yet Paid!");
        Expert expert = expertService.findById(offer.getExpert().getId());
        if (order.getWorkEndDate().compareTo(offer.getProposedEndDate()) > 0) {
            long timeDifference = ChronoUnit.HOURS.between(offer.getProposedEndDate(), order.getWorkEndDate());
            expert.setRate(expert.getRate() - (int) timeDifference);
            expertService.update(expert);
        }
    }

    @Override
    @Transactional
    public void deActiveExpert(Long expertId) {
        Expert expert = expertService.findById(expertId);
        if (expert.getRate() > 0 )
            throw new ExpertActivationException("this expert rate is positive");
        if(expert.getIsActive().equals(false))
            throw new ExpertActivationException("this expert account is inactive");
        expertService.changeExpertAccountActivation(expertId, false);
    }

    @Override
    public List<FilterExpertResponseDTO> expertFilter(FilterExpertDTO expertDTO) {
        return expertService.expertsFilter(expertDTO);
    }

    @Override
    public List<FilterCustomerResponseDTO> customerFilter(FilterCustomerDTO customerDTO) {
        return customerService.customersFilter(customerDTO);
    }
    @Override
    public List<ExpertResponseDTO> showSubServicesExpert(Long subServiceId){
        SubService subService = subServiceService.findById(subServiceId);
        List<ExpertResponseDTO> expertResponseDTOList = new ArrayList<>();
        for (Expert expert: subService.getExperts())
            expertResponseDTOList.add(ExpertMapper.INSTANCE.modelToResponseDto(expert));
        return expertResponseDTOList;
    }
}
