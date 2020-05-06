package com.eglobal.bo.api.zip.password;

import java.util.Random;

public class PasswordGenerator {

	public static final String VERSION = ("1.0");

    public static final String CHARACTER_SET = ("aA1bB2cC3dD4eE5fF6gG8hH9iI0jJkKlLmMnNoOpP&qQrRsStT_uUvV-wWxX+yYzZ");

    public static void main(String[] args) {
      System.out.println("Welcome to password generator, version: " + VERSION);
      System.out.println(generatePassword(20));
    }

    private static String generatePassword(int passwordLength) {
      String password = "";
      for (int i = 0; i < passwordLength; i++) {
        password = password + " ";
      }
      Random random = new Random();
      for (int i = 0; i < password.length(); i++) {
        if (i == 0) {
          password = String.valueOf(CHARACTER_SET.charAt(random.nextInt(65))) + password.substring(1, password.length());
        } else {
          password = password.substring(0, i) + String.valueOf(CHARACTER_SET.charAt(random.nextInt(65))) + password.substring(i + 1, password.length());
        }
      }
      return password.toString();
    }
}