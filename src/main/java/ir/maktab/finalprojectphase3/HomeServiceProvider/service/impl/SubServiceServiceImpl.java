package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.SubServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.BaseServiceMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.SubServiceMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.SubServiceRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.SubServiceService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.BaseServiceValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.SubServiceValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;

    @Override
    public void add(SubServiceRequestDTO subServiceRequestDTO) {
        SubService subService = SubServiceMapper.INSTANCE.requestDtoToModel(subServiceRequestDTO);
        BaseServiceValidator.isNotExistService(subService.getBaseService().getName());
        SubServiceValidator.isExistSubService(subService.getName());
        subServiceRepository.save(subService);
    }

    @Override
    public void remove(SubServiceRequestDTO subServiceRequestDTO) {
        SubService subService = SubServiceMapper.INSTANCE.requestDtoToModel(subServiceRequestDTO);
        subService.setDeleted(true);
        subServiceRepository.save(subService);
    }

    @Override
    public void update(SubServiceRequestDTO subServiceRequestDTO) {
        SubService subService = SubServiceMapper.INSTANCE.requestDtoToModel(subServiceRequestDTO);
        subServiceRepository.save(subService);
    }

    @Override
    public SubServiceResponseDTO findByName(String subServiceName) {
        SubService subService = subServiceRepository.findByName(subServiceName).orElseThrow(() -> new NotFoundException("not found"));
        return SubServiceMapper.INSTANCE.modelToResponseDto(subService);
    }

    @Override
    public List<SubServiceResponseDTO> selectAll() {
        List<SubService> subServiceList = subServiceRepository.findAll();
        List<SubServiceResponseDTO> subServiceResponseDTOList = new ArrayList<>();
        for (SubService subService: subServiceList)
            subServiceResponseDTOList.add(SubServiceMapper.INSTANCE.modelToResponseDto(subService));
        return subServiceResponseDTOList;
    }

    @Override
    public List<SubServiceResponseDTO> selectAllAvailableService() {
        List<SubService> subServiceList = subServiceRepository.findAllByDeletedIs(false);
        List<SubServiceResponseDTO> subServiceResponseDTOList = new ArrayList<>();
        for (SubService subService: subServiceList)
            subServiceResponseDTOList.add(SubServiceMapper.INSTANCE.modelToResponseDto(subService));
        return subServiceResponseDTOList;
    }

    @Override
    public List<SubServiceResponseDTO> getSubServicesByService(BaseServiceRequestDTO baseServiceRequestDTO) {
        List<SubService> subServiceList = subServiceRepository.findAllByBaseService(BaseServiceMapper.INSTANCE.requestDtoToModel(baseServiceRequestDTO));
        List<SubServiceResponseDTO> subServiceResponseDTOList = new ArrayList<>();
        for (SubService subService: subServiceList)
            subServiceResponseDTOList.add(SubServiceMapper.INSTANCE.modelToResponseDto(subService));
        return subServiceResponseDTOList;
    }
}
