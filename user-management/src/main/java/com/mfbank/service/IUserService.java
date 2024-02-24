package com.mfbank.service;

import com.mfbank.dto.Userdto;
import com.mfbank.model.User;

import java.util.List;

public interface IUserService {
    public List<Userdto> retrieveAllUsers();
    public Userdto retrieveUser(String userName);
    public void addUser(Userdto userdto);
    public void removeUser(String userName);
    public User modifyUser(Userdto userdto);
}
