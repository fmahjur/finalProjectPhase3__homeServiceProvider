package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.BaseServiceMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.BaseService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.BaseServiceRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.BaseServiceService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.BaseServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BaseServiceServiceImpl implements BaseServiceService {
    private final BaseServiceRepository baseServiceRepository;

    @Override
    public void add(BaseServiceRequestDTO baseServiceRequestDTO) {
        BaseServiceValidator.isExistService(baseServiceRequestDTO.getName());
        BaseService baseService = BaseServiceMapper.INSTANCE.requestDtoToModel(baseServiceRequestDTO);
        baseServiceRepository.save(baseService);
    }

    @Override
    public void remove(BaseServiceRequestDTO baseServiceRequestDTO) {
        BaseService baseService = BaseServiceMapper.INSTANCE.requestDtoToModel(baseServiceRequestDTO);
        baseService.setDeleted(true);
        baseServiceRepository.save(baseService);
    }

    @Override
    public void update(BaseServiceRequestDTO baseServiceRequestDTO) {
        BaseService baseService = BaseServiceMapper.INSTANCE.requestDtoToModel(baseServiceRequestDTO);
        baseServiceRepository.save(baseService);
    }

    @Override
    public BaseServiceResponseDTO findByName(String baseServiceName) {
        BaseService baseService = baseServiceRepository.findByName(baseServiceName).orElseThrow(() -> new NotFoundException("not found"));
        return BaseServiceMapper.INSTANCE.modelToResponseDto(baseService);
    }

    @Override
    public List<BaseServiceResponseDTO> selectAll() {
        List<BaseService> baseServiceList = baseServiceRepository.findAll();
        List<BaseServiceResponseDTO> baseServiceResponseDTOList = new ArrayList<>();
        for (BaseService baseService : baseServiceList)
            baseServiceResponseDTOList.add(BaseServiceMapper.INSTANCE.modelToResponseDto(baseService));
        return baseServiceResponseDTOList;
    }

    @Override
    public List<BaseServiceResponseDTO> selectAllAvailableService() {
        List<BaseService> baseServiceList = baseServiceRepository.findAllByDeletedIs(false);
        List<BaseServiceResponseDTO> baseServiceResponseDTOList = new ArrayList<>();
        for (BaseService baseService : baseServiceList)
            baseServiceResponseDTOList.add(BaseServiceMapper.INSTANCE.modelToResponseDto(baseService));
        return baseServiceResponseDTOList;
    }
}
