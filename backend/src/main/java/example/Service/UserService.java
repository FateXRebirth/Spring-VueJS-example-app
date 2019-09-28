package example.Service;

import example.Repository.LogRepository;
import example.Repository.UserRepository;
import example.Request.*;
import example.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogRepository logRepository;

    public Result sign_in(MemberLogin memberLogin) {
        return userRepository.login(memberLogin);
    }

    public Result sign_up(MemberRegister memberRegister) {
        return userRepository.register(memberRegister);
    }

    public Result login(int MemberID, String time) { return logRepository.login(MemberID, time); }

    public Result logout(int MemberID, String time) { return logRepository.logout(MemberID, time); }

    public Result getUsers() { return userRepository.getUsers(); }

    public Result getUserByID(int MemberID) { return userRepository.getUserByID(MemberID); }

    public Result editUserByID(int MemberID, MemberEdit memberEdit) { return userRepository.editUserByID(MemberID, memberEdit); }

    public Result upgradeUserByID(int MemberID, MemberUpgrade memberUpgrade) { return userRepository.upgradeUserByID(MemberID, memberUpgrade); }

    public Result getFavoriteCars(int MemberID) { return userRepository.getFavoriteCars(MemberID); }

    public Result addFavoriteCars(int MemberID, int CarID) { return userRepository.addFavoriteCars(MemberID, CarID); }

    public Result removeFavoriteCars(int MemberID, int CarID) { return userRepository.removeFavoriteCars(MemberID, CarID); }

}
