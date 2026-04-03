package vn.hoidanit.jobhunter.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.error.IdInvalidException;
import vn.hoidanit.jobhunter.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return this.userRepository.save(user);
    }

    public void deleteUser(long id){
        this.userRepository.deleteById(id);
    }

    public User findById(long id) throws IdInvalidException{
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new IdInvalidException("id không tồn tại");
        }
        return optionalUser.get();
    }

    public List<User> finaAllUsers(){
        return this.userRepository.findAll();
    }


}
