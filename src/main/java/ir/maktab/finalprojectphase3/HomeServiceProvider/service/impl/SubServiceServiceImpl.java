package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.SubServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UpdateSubServiceDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.BaseServiceMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.SubServiceMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.BaseService;
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
    private final BaseServiceServiceImpl baseServiceService;

    @Override
    public void add(SubServiceRequestDTO subServiceRequestDTO) {
        SubService subService = SubServiceMapper.INSTANCE.requestDtoToModel(subServiceRequestDTO);
        BaseServiceValidator.isNotExistService(subServiceRequestDTO.getBaseServiceRequestID());
        SubServiceValidator.isExistSubService(subService.getName());
        subService.setBaseService(getBaseServiceObject(subServiceRequestDTO.getBaseServiceRequestID()));
        subServiceRepository.save(subService);
    }

    @Override
    public void remove(SubServiceRequestDTO subServiceRequestDTO) {
        SubService subService = SubServiceMapper.INSTANCE.requestDtoToModel(subServiceRequestDTO);
        subService.setDeleted(true);
        subServiceRepository.save(subService);
    }

    @Override
    public void update(UpdateSubServiceDTO updateSubServiceDTO) {
        SubService subService = subServiceRepository.findById(updateSubServiceDTO.getSubServiceID())
                .orElseThrow(()->new NotFoundException("not found"));
        subService.setBaseService(getBaseServiceObject(updateSubServiceDTO.getBaseServiceRequestID()));
        subService.setName(updateSubServiceDTO.getName());
        subService.setDescription(updateSubServiceDTO.getDescription());
        subService.setBasePrice(updateSubServiceDTO.getBasePrice());
        subServiceRepository.save(subService);
    }

    @Override
    public SubService findByName(String subServiceName) {
        return subServiceRepository.findByName(subServiceName).orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public SubService findById(Long subServiceId) {
        return subServiceRepository.findById(subServiceId).orElseThrow(() -> new NotFoundException("not found"));
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
    public List<SubServiceResponseDTO> getSubServicesByService(Long baseServiceRequestId) {
        BaseService baseService = baseServiceService.findById(baseServiceRequestId);
        List<SubService> subServiceList = subServiceRepository.findAllByBaseService(baseService);
        List<SubServiceResponseDTO> subServiceResponseDTOList = new ArrayList<>();
        for (SubService subService: subServiceList)
            subServiceResponseDTOList.add(SubServiceMapper.INSTANCE.modelToResponseDto(subService));
        return subServiceResponseDTOList;
    }

    private BaseService getBaseServiceObject(Long id){
        BaseService baseService = new BaseService();
        baseService.setId(id);
        return baseService;
    }
}
