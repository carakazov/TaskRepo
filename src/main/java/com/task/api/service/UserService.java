package com.task.api.service;

import com.task.api.business.Hasher;
import com.task.api.business.exceptions.handler.Validator;
import com.task.api.dao.IUserDAO;
import com.task.api.dto.AuthorizationUserDTO;
import com.task.api.dto.InputUserDTO;
import com.task.api.dto.UserDTO;
import com.task.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserDAO userDAO;
    private final Validator catcher = new Validator();
    @Autowired
    public UserService(IUserDAO userDAO){
        this.userDAO = userDAO;
        catcher.setUserDAO(userDAO);
    }

    //Добавить нового пользователя.
    public String add(InputUserDTO newUser){
        catcher.checkNewUser(newUser);
        int login = Hasher.hashString(newUser.getLoginAndPassword().getLogin());
        int password = Hasher.hashString(newUser.getLoginAndPassword().getPassword());
        User user = new User(login, password, newUser.getUserDTO().getName(), newUser.getUserDTO().getSurname(), newUser.getUserDTO().getBirthDate());
        userDAO.save(user);
        return "User added";
    }


    //Авторизация пользователя.
    public UserDTO authorize(AuthorizationUserDTO authorizationUserDTO){
        catcher.checkAuthorizationUser(authorizationUserDTO);
        int login = Hasher.hashString(authorizationUserDTO.getLogin());
        User user = userDAO.getByLogin(login);
        return new UserDTO(user.getName(), user.getSurname(), user.getBirthDate());
    }
}
