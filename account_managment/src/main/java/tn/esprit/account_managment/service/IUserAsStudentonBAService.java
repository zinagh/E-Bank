package tn.esprit.account_managment.service;
import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.model.UserAsStudentonBA;
import java.util.List;

public interface IUserAsStudentonBAService
{

    List<UserAsStudentonBADto> retrieveAllUserAsStudentonBAs();
    UserAsStudentonBA retrieveUserAsStudentonBA(String userAsStudentonBAId);
    UserAsStudentonBA addUserAsStudentonBA(UserAsStudentonBA u);
    void removeUserAsStudentonBA(String  userAsStudentonBAId);
    UserAsStudentonBA modifyUserAsStudentonBA(UserAsStudentonBA userAsStudentonBA);
}
