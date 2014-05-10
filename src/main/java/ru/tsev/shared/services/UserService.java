package ru.tsev.shared.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.tsev.shared.dao.common.CrudOperations;
import ru.tsev.shared.dto.UserDTO;

@RemoteServiceRelativePath("springGwtServices/userService")
public interface UserService extends CrudOperations<UserDTO>, RemoteService {

}
