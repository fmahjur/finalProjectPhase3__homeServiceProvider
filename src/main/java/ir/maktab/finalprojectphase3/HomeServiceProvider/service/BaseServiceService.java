package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.BaseService;

import java.util.List;

public interface BaseServiceService {
    void add(BaseServiceRequestDTO baseServiceRequestDTO);

    void remove(Long baseServiceId);

    void update(BaseServiceRequestDTO baseServiceRequestDTO);

    BaseServiceResponseDTO findByName(String baseServiceName);

    BaseService findById(Long baseServiceId);

    List<BaseServiceResponseDTO> selectAll();

    List<BaseServiceResponseDTO> selectAllAvailableService();
}
