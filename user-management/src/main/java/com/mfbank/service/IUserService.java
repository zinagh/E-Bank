package com.mfbank.service;

import com.mfbank.dto.Userdto;
import com.mfbank.model.User;

import java.util.List;

public interface IUserService {
    List<Userdto> retrieveAllUsers();
    Userdto retrieveUser(String userName);
    void addUser(Userdto userdto);
    void removeUser(String userName);
    User modifyUser(Userdto userdto);
    String updatepassword(String username, String newwpass ,String verifpass);

}
