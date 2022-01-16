package br.com.vehiclessales.demo.domain.services;

import br.com.vehiclessales.demo.domain.entities.User;
import br.com.vehiclessales.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;


    public String login(User user) throws Exception {
        User result = userRepository.findByEmail(user.getEmail());
        if(result != null){
            if(!result.getPassword().equals(user.getPassword())){
                throw new IllegalAccessException("invalid password");
            } else {
                String token = tokenService.generateToken(result);
                String role = getRoleById(tokenService.getUserId(token).get());

                return token;
            }
        } else {
            throw new IllegalAccessException("user not found");
        }
    }


    public String getRoleById(String id){
        return userRepository.getRoleById(id).toString();
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
