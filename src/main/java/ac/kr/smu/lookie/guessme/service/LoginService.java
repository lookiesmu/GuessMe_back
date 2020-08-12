package ac.kr.smu.lookie.guessme.service;

import ac.kr.smu.lookie.guessme.config.security.JwtTokenProvider;
import ac.kr.smu.lookie.guessme.domain.User;
import ac.kr.smu.lookie.guessme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Map<String, String> checkLogin(String nickname, String password){
        Map<String, String> returnJson = new HashMap<>();
        Optional<User> user = userRepo.findByNickname(nickname);
        if(!user.isPresent()||!passwordEncoder.matches(password, user.get().getPassword())){
            returnJson.put("success",null);
        }
        else{
            returnJson.put("nickname",user.get().getNickname());
            returnJson.put("token",jwtTokenProvider.createToken(String.valueOf(user.get().getUserId()), user.get().getRoles()));
        }
        return returnJson;
    }
}
