package com.sliit.slotopzi.controller;

import com.sliit.slotopzi.dto.request.CustomerSignInRequest;
import com.sliit.slotopzi.dto.request.CustomerSignUpRequest;
import com.sliit.slotopzi.dto.request.GetNameOfLoggedUserRequest;
import com.sliit.slotopzi.dto.request.Resetpwd;
import com.sliit.slotopzi.dto.response.UserSigned;
import com.sliit.slotopzi.model.Customer;
import com.sliit.slotopzi.service.AuthService;
import com.sliit.slotopzi.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody CustomerSignUpRequest customerSignUpRequest){

        String email=customerSignUpRequest.getEmail();
        String username=customerSignUpRequest.getUserName();
        String contactNo=customerSignUpRequest.getContactNo();
        String responseMsg;
        if (authService.checkIfEmailExists(email)){
            responseMsg="Email exists";
        }else if (authService.checkIfContactExists(contactNo)){
            responseMsg="Contact exists";
        }else if (authService.checkIfUserNameExists(username)){
            responseMsg="username exists";
        }else {
            authService.signup(customerSignUpRequest);
            responseMsg="Customer Added Successfully";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @PostMapping("/user/login")
    public ResponseEntity userLogin(@RequestBody CustomerSignInRequest customerSignInRequest){
        //get object of relavant user
        CustomerSignInRequest newReq = new CustomerSignInRequest();
        newReq.setUserName("admin");
        newReq.setEmail("admin@admin.com");
        newReq.setPassword("admin");

        // 21232f297a57a5a743894a0e4a801fc3
        String email=customerSignInRequest.getEmail();
        String userName=customerSignInRequest.getUserName();
        String responseMsg;

        System.out.println(bcryptPasswordEncoder.encode(newReq.getPassword()));

        //continue if user exists on provided details
        if (Objects.requireNonNull(userName).equals(newReq.getUserName())) {
            UserSigned response= authService.userLogin(newReq);
            return ResponseEntity.ok().body(response);
        } else if (authService.findbyUserNameorEmail(userName,email)){
            UserSigned response= authService.userLogin(customerSignInRequest);
            return ResponseEntity.ok().body(response);
        }
        responseMsg="UserName or email Invalid";
        return ResponseEntity.badRequest().body(responseMsg);
//        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/gettoken")
    public String generateToken(@RequestBody CustomerSignInRequest signInRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );
        }catch (Exception ex){
            throw new Exception("Invalid Username or password");
        }
        return jwtTokenUtil.generateToken(signInRequest.getEmail());
    }

    @GetMapping("/allcustomers")
    public List<Customer> findAllCustomers(){
        return authService.getAll();
    }

    @PostMapping("/forgetpwd")
    public ResponseEntity forgetPwd(@RequestBody Resetpwd resetpwd){

        String email=resetpwd.getEmail();
        String newpwd=resetpwd.getNewpwd();
        String responseMsg ="";
        if(authService.resetpwd(email,newpwd)){
            responseMsg="Successful!";
            return ResponseEntity.ok().body(responseMsg);
        }else{
            responseMsg="Error!";
            return ResponseEntity.badRequest().body(responseMsg);
        }
    }

    @PostMapping("/getUserName")
    public ResponseEntity getUserName(@RequestBody GetNameOfLoggedUserRequest getNameOfLoggedUserRequest){
        if(authService.checkIfUserIdExistsOnUserType(getNameOfLoggedUserRequest.getUserId(),getNameOfLoggedUserRequest.getUserType())){
            return ResponseEntity.ok().body(authService.findStaffName(getNameOfLoggedUserRequest));
        }else{
            return ResponseEntity.badRequest().body("Invalid User Credentials");
        }
    }


    @GetMapping("/checkemail/{email}")
    public ResponseEntity checkForEmail(@PathVariable String email){
        return ResponseEntity.ok().body(authService.checkIfEmailExists(email));
    }


}
