package tn.esprit.account_managment.service;
import tn.esprit.account_managment.model.UserAsStudentonBA;
import java.util.List;

public interface IUserAsStudentonBAService
{
    public List<UserAsStudentonBA> retrieveAllUserAsStudentonBAs();
    public UserAsStudentonBA retrieveUserAsStudentonBA(String userAsStudentonBAId);
    public UserAsStudentonBA addUserAsStudentonBA(UserAsStudentonBA u);
    public void removeUserAsStudentonBA(String  userAsStudentonBAId);
    public UserAsStudentonBA modifyUserAsStudentonBA(UserAsStudentonBA userAsStudentonBA);
}
