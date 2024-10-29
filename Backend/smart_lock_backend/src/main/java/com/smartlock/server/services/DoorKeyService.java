package com.smartlock.server.services;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.smartlock.server.entities.DoorKey;

public class DoorKeyService {
    public static String encryptData(DoorKey doorKey) throws Exception {
        int keyId = doorKey.getKeyID();
        String publicKeyStr = doorKey.getKeyValue();

        publicKeyStr = publicKeyStr
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");


        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Map<String, Object> data = new HashMap<>();
        ZonedDateTime expireDateTime = ZonedDateTime.now(ZoneId.systemDefault()).plusSeconds(120).plusHours(1);
        data.put("id", keyId);
        data.put("expire_date", expireDateTime.toInstant().toString());

        System.out.println(expireDateTime.toInstant().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(data);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = cipher.doFinal(jsonData.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(encryptedMessage);
    }

}
