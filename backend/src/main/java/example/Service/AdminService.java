package example.Service;

import example.Entity.Admin;
import example.Repository.AdminRepository;
import example.Request.Login;
import example.Request.Register;
import example.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Result login(Login login) {
        return adminRepository.login(login);
    }

    public Result register(Register register) {
        return adminRepository.register(register);
    }
}
