package ru.tsev.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsev.server.dao.UserDao;
import ru.tsev.shared.dao.common.CrudOperations;
import ru.tsev.shared.dto.UserDTO;
import ru.tsev.shared.services.UserService;
import ru.tsev.shared.services.common.AbstractService;

@Service("userService")
public class UserServiceImpl extends AbstractService<UserDTO> implements UserService {

    @Autowired
    private UserDao dao;

    public UserServiceImpl() {
        super();
    }

    @Override
    protected CrudOperations<UserDTO> getDao() {
        return dao;
    }
}