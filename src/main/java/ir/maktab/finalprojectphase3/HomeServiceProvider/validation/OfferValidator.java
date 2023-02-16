package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OfferMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;

public class OfferValidator {
    public static void isValidExpertProposedPrice(OfferRequestDTO offerRequestDTO) throws ValidationException {
        Offer offer = OfferMapper.INSTANCE.requestDtoToModel(offerRequestDTO);
        if (!(offer.getOfferPrice() >= offer.getOrders().getSubService().getBasePrice()))
            throw new ValidationException("The proposed price cannot be less than the base price!");
    }

    public static void hasDurationOfWork(OfferRequestDTO offerRequestDTO){
        Offer offer = OfferMapper.INSTANCE.requestDtoToModel(offerRequestDTO);
        if (offer.getDurationOfWork() <= 0) {
            throw new IncorrectInformationException("Your offer must include the duration of the work");
        }
    }
}
