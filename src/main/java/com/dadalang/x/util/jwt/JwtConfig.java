package com.dadalang.x.util.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/5/20 11:29 上午
 * @desc
 */
public class JwtConfig {

    private static String secret = "sectet*12U234.333";
    private String header = "token";
    private static int duration = 7 * 24 * 60;

    public static String token(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, duration);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        builder.withExpiresAt(calendar.getTime());
        return builder.sign(Algorithm.HMAC256(secret));
    }
    public static Map<String, Claim> validate(String token) {
        try{
            DecodedJWT result = JWT
                    .require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token);
            return result.getClaims();
        }catch (TokenExpiredException e) {
            e.printStackTrace();
        }catch (SignatureVerificationException e) {
            e.printStackTrace();
        }catch (AlgorithmMismatchException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
