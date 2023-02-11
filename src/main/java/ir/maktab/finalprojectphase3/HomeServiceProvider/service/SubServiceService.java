package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.SubServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;

import java.util.List;

public interface SubServiceService {
    void add(SubServiceRequestDTO subServiceRequestDTO);

    void remove(SubServiceRequestDTO subServiceRequestDTO);

    void update(SubServiceRequestDTO subServiceRequestDTO);

    SubServiceResponseDTO findByName(String subServiceName);

    List<SubServiceResponseDTO> selectAll();

    List<SubServiceResponseDTO> selectAllAvailableService();

    List<SubServiceResponseDTO> getSubServicesByService(BaseServiceRequestDTO baseServiceRequestDTO);
}
