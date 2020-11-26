package com.controller;

import com.App;
import com.testCasePrint;
import com.helper.PropertyHelper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RequestMapping(App.service + "/account")
@RestController
public class AccountController {
  @GetMapping("/jwt-id")
  public Response<Object> getUserId(
    @RequestParam(name = "token", required = true) String token
  ) throws Exception {
    String secretKey = PropertyHelper.getJWTSecretKey();
    testCasePrint.logErrorToTerminal(secretKey);

    Jws<Claims> result = Jwts
      .parserBuilder()
      .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
      .build().parseClaimsJws(token);
    
    return ResponseHandler.ok(result.getBody());
  }
}