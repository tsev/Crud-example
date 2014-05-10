package ru.tsev.server.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsev.server.dao.UserDao;
import ru.tsev.shared.dao.common.AbstractDao;
import ru.tsev.shared.dto.UserDTO;


@Repository
public class UserDaoImpl extends AbstractDao<UserDTO> implements UserDao {

    public UserDaoImpl() {
        super();

        setClazz(UserDTO.class);
    }
}