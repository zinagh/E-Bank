package tn.esprit.card_management.services;

import tn.esprit.card_management.dto.Feedto;
import tn.esprit.card_management.model.Fee;

import java.util.List;

public interface IFeeService {
    List<Feedto> retrieveAllFees();
    Fee addFee(Feedto feedto);
    void removeFee(Long Id);
    Feedto retrieveFee(Long Id);
    Fee modifyFee(Feedto feedto);
}
