package com.kenny.Authentication.system.service.impl;

import com.kenny.Authentication.system.dto.ForgotPasswordRequest;
import com.kenny.Authentication.system.dto.LoginRequest;
import com.kenny.Authentication.system.dto.Request;
import com.kenny.Authentication.system.dto.Response;
import com.kenny.Authentication.system.entities.User;
import com.kenny.Authentication.system.repository.UserRepository;
import com.kenny.Authentication.system.securityConfig.JwtTokenProvider;
import com.kenny.Authentication.system.service.OtpService;
import com.kenny.Authentication.system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

//@Autowired
//    private  ModelMapper modelMapper;


   private UserRepository userRepository;

    @Lazy
    private PasswordEncoder passwordEncoder;


    private AuthenticationManager authenticationManager;


    private JwtTokenProvider jwtTokenProvider;


    private OtpService otpService;

    @Override
    public ResponseEntity<Response> signUp(Request request) {
        //if the user exists - return error
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .responseMessage("this account is  already in use")
                    .build());
        }
        //if password doesn't match

        if (request.getPassword().equals(request.getConfirmPassword())) {
            final String ids = UUID.randomUUID().toString().replace("-", "");
                  User user = User.builder()
                    .id(ids)
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .isTermsAndCondition(request.getIsTermsAndCondition())
                    .build();
            if (request.getIsTermsAndCondition()) {
                User savedUser = userRepository.save(user);
                return ResponseEntity.ok(Response.builder().build());

            }else {
                return ResponseEntity.ok(Response.builder().responseMessage("agree with terms and conditions").build());
            }
        }
        return ResponseEntity.ok(Response.builder()
                .responseMessage("password does not match").build());
    }

    public Response changePassword(ForgotPasswordRequest forgotPasswordRequest, String email) {
        User user1 = userRepository.findByEmail(email).orElseThrow(()
                -> new DataRetrievalFailureException("not found"));

if(forgotPasswordRequest.getPassword().equals(forgotPasswordRequest.getConfirmPassword())) {
    user1.setPassword(passwordEncoder.encode(forgotPasswordRequest.getPassword()));
    userRepository.save(user1);
    return Response.builder().responseMessage("SUCCESS").build();
}return Response.builder().responseMessage(" password does not match ").build();
    }



    public String deleteUserById (String  id){

        userRepository.deleteById(id);
        return "user deleted successfully";

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("user with this email is not found");
        }
        return  new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), null);
    }





    @Override
    public Response login(LoginRequest login) {

        Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (login.getEmail(), login.getPassword()));
        return Response.builder().responseMessage(jwtTokenProvider.generateToken(authentication)).build();
    }

    @Override
    public Response sendOtp() {
        return null;
    }

    @Override
    public Response validateOtp() {
        return null;
    }

    @Override
    public Response resetPassword() {
        return null;
    }




}
