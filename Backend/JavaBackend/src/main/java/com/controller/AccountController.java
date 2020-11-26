package com.controller;

import com.App;
import com.helper.PropertyHelper;
import com.testCasePrint;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(App.service + "/account")
@RestController
public class AccountController {

  @GetMapping("/jwt-id")
  public Response<Object> getUserId(
    @RequestParam(name = "token", required = true) String token
  ) {
    try {
      testCasePrint.logErrorToTerminal(PropertyHelper.getJWTSecretKey());

      Algorithm algorithm = Algorithm.HMAC256(PropertyHelper.getJWTSecretKey());
      JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer("localhost")
          .build();
      DecodedJWT jwt = verifier.verify(token);

      return ResponseHandler.ok(jwt);
    } catch (Exception e) {
      return ResponseHandler.error(null);
    }
  }
}
