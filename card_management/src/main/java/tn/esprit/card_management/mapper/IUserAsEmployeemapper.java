package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.UserAsEmployeedto;

import tn.esprit.card_management.model.UserAsEmployee;

import java.util.List;

public interface IUserAsEmployeemapper {
    UserAsEmployeedto fromentityTodto(UserAsEmployee userAsEmployee);

    UserAsEmployee fromdtoToentity(UserAsEmployeedto userAsEmployeedto);
    List<UserAsEmployeedto> fromListentityTodtos (List<UserAsEmployee> userAsEmployees);

    List<UserAsEmployee>fromListdtosToentities(List <UserAsEmployeedto> userAsEmployeedtos);
}
