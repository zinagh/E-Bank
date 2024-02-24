package tn.esprit.account_managment.service;
import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.model.UserAsStudentonBA;
import java.util.List;

public interface IUserAsStudentonBAService
{

    List<UserAsStudentonBADto> retrieveAllUserAsStudentonBAs();
    UserAsStudentonBADto retrieveUserAsStudentonBA(String userAsStudentonBAId);
    void addUserAsStudentonBA(UserAsStudentonBADto userAsStudentonBADto);
    void removeUserAsStudentonBA(String  userAsStudentonBAId);
    void modifyUserAsStudentonBA(UserAsStudentonBADto userAsStudentonBAdto);
}
