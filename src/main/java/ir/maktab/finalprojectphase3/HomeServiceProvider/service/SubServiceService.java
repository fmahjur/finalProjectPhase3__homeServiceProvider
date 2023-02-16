package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.SubServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UpdateSubServiceDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;

import java.util.List;

public interface SubServiceService {
    void add(SubServiceRequestDTO subServiceRequestDTO);

    void remove(SubServiceRequestDTO subServiceRequestDTO);

    void update(UpdateSubServiceDTO updateSubServiceDTO);

    SubService findByName(String subServiceName);
    SubService findById(Long subServiceId);

    List<SubServiceResponseDTO> selectAll();

    List<SubServiceResponseDTO> selectAllAvailableService();

    List<SubServiceResponseDTO> getSubServicesByService(Long baseServiceRequestId);
}
