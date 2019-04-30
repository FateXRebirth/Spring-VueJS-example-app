package example.Service;

import example.Repository.UserRepository;
import example.Request.MemberLogin;
import example.Request.MemberEdit;
import example.Request.MemberUpgrade;
import example.Request.MemberRegister;
import example.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Result login(MemberLogin memberLogin) {
        return userRepository.login(memberLogin);
    }

    public Result register(MemberRegister memberRegister) {
        return userRepository.register(memberRegister);
    }

    public Result getUsers() {
        return userRepository.getUsers();
    }

    public Result getUserByID(int id) {
        return userRepository.getUserByID(id);
    }

    public Result editUserByID(int id, MemberEdit memberEdit) { return userRepository.editUserByID(id, memberEdit); }

    public Result upgradeUserByID(int id, MemberUpgrade memberUpgrade) { return userRepository.upgradeUserByID(id, memberUpgrade); }
}
