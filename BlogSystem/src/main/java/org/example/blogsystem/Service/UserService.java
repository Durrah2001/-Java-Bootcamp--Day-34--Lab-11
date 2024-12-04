package org.example.blogsystem.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.blogsystem.ApiResponse.ApiException;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){

        User u = userRepository.findUserByUserId(id);

        if(u == null)
            throw new ApiException("User with this ID not found to update it!");

        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRegistrationDate(LocalDate.now());
        userRepository.save(u);


    }

    public void deleteUser(Integer id){

        User u= userRepository.findUserByUserId(id);


        if(u == null)
            throw new ApiException("User with this ID not found to delete it!");

        userRepository.delete(u);
    }
    /////////
    //7: get users registered in range date

    public List<User> getUsersRegisteredByDate(LocalDate date1, LocalDate date2){

        List<User> users = userRepository.getUsersRegisteredByDate(date1, date2);

        if(users.isEmpty())
            throw new ApiException("There is no user registered in this date range!");

        return users;
    }




}
