package com.chhd.y.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class JwtUtils {

    @Autowired
    public HttpServletRequest request;

    public static DecodedJWT verifyJwt(String token) {
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            decodedJWT = jwtVerifier.verify(token);
        } catch (Exception ignore) {

        }
        return decodedJWT;
    }

    public static String getString(String token, String key) {
        return verifyJwt(token).getClaim(key).asString();
    }

    public static Long getLong(String token, String key) {
        return verifyJwt(token).getClaim(key).asLong();
    }

    public static String getToken() {
        return new JwtUtils().request.getHeader("token");
    }
}
