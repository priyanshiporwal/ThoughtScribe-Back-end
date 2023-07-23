package com.spring.BlogApp.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.BlogApp.Dto.UserDto;
import com.spring.BlogApp.Models.Login;
import com.spring.BlogApp.Models.User;
import com.spring.BlogApp.Payloads.JwtResponse;
import com.spring.BlogApp.Respository.UserRepo;
import com.spring.BlogApp.Utils.JwtUtils;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/public/login")
	public ResponseEntity<JwtResponse> login(@RequestBody Login user ){
		UserDto userDto=null;
		try {
			
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JwtResponse jwtResponse=new JwtResponse("User not found",userDto);
			return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.NOT_FOUND);
			
		}
		String token=jwtUtils.generateJwtToken(user.getUsername());	
		UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUsername());
		User user2=modelMapper.map(userDetails, User.class);
		userDto=modelMapper.map(user2, UserDto.class);
		JwtResponse jwtResponse=new JwtResponse(token,userDto);
		return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.OK);
		
	}
	
	
}
