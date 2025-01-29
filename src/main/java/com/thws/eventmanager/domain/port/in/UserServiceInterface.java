package com.thws.eventmanager.domain.port.in;
import com.thws.eventmanager.domain.models.User;

public interface UserServiceInterface {
    public User createUser(User user);
    public User validateUser(User user);
    public boolean deleteUser(User user);
    public User updateUser(User user);
}
