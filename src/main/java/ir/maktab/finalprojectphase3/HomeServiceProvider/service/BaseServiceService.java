package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;

import java.util.List;

public interface BaseServiceService {
    void add(BaseServiceRequestDTO baseServiceRequestDTO);

    void remove(BaseServiceRequestDTO baseServiceRequestDTO);

    void update(BaseServiceRequestDTO baseServiceRequestDTO);

    BaseServiceResponseDTO findByName(String baseServiceName);

    List<BaseServiceResponseDTO> selectAll();

    List<BaseServiceResponseDTO> selectAllAvailableService();
}
