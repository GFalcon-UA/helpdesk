package com.javamog.potapov;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncode {

    public static void main(String[] args){
        String pass = "P@ssword1";
        PasswordEncoder passEncoder = new BCryptPasswordEncoder();
        System.out.println(passEncoder.encode(pass));
        System.out.println(passEncoder.encode(pass));
        System.out.println(passEncoder.encode(pass));
        System.out.println(passEncoder.encode(pass));
        System.out.println(passEncoder.encode(pass));
        System.out.println(passEncoder.encode(pass));

        pass = "P@ssword1";
        String encoded1 = "$2a$10$xlEHWdEmaWDJNbsFIkhoHu5OJNKjr5Ixp/LL3/9QZdrxF2CVdzhQy";
        String encoded2 = "$2a$10$Mhoo0EZJv0b/LjgiQy7byume9di9TwqC/nXAjGLk9aUZ7qFLuTDcC";
        String encoded3 = "$2a$10$xA45bdEktvREDiKgvJIxde3g/nQSAjgosrrdaGxtLnV762bC.fcW6";
        String encoded4 = "$2a$10$Ds41FibdOdYm3Uq5x3Ki6enq3/UCfi7EDRf1Fo47AQZii7NnWdIzm";
        String encoded5 = "$2a$10$pdGBpiwO3DbdBcusKeXRu.A1QpnC0W.bIoLT1TctpYC3DEVAQ7q8y";
        String encoded6 = "$2a$10$rAJgMktEgEg6iTyI9lR/JOg03vAS2i9dGFIpat9SC49K6SLLPPsgW";
        System.out.println(passEncoder.matches(pass, encoded1));
        System.out.println(passEncoder.matches(pass, encoded2));
        System.out.println(passEncoder.matches(pass, encoded3));
        System.out.println(passEncoder.matches(pass, encoded4));
        System.out.println(passEncoder.matches(pass, encoded5));
        System.out.println(passEncoder.matches(pass, encoded6));
    }

}
