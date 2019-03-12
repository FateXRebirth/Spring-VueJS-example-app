package example.Service;

import example.Repository.UserRepository;
import example.Request.Login;
import example.Request.Register;
import example.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Result login(Login login) {
        return userRepository.login(login);
    }

    public Result register(Register register) {
        return userRepository.register(register);
    }
}
