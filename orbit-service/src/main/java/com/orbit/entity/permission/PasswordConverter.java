package com.orbit.entity.permission;

import org.springframework.security.crypto.codec.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 完成对password字段的加解密
 */
@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

  private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
  private static final byte[] KEY = "MySuperSecretKey".getBytes();

  @Override
  public String convertToDatabaseColumn(String password) {
    // do some encryption
    Key key = new SecretKeySpec(KEY, "AES");
    try {
      Cipher c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.ENCRYPT_MODE, key);
      return new String(Base64.encode(c.doFinal(password.getBytes())));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    // do some decryption
    Key key = new SecretKeySpec(KEY, "AES");
    try {
      Cipher c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.DECRYPT_MODE, key);
      return new String(c.doFinal(Base64.decode(dbData.getBytes())));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
