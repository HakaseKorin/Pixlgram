package com.cognizant.crustaceanspostcrud.utils;

import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

	public boolean validate(String accesstoken, String issuer) {
		if (accesstoken == null || accesstoken.equals(""))
			return false;

		String[] chunk = accesstoken.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String payload = new String(decoder.decode(chunk[1]));

		try {
			Map<String, Object> result = new ObjectMapper().readValue(payload,
					new TypeReference<Map<String, Object>>() {
					});

			boolean valid = false;
			int expiration = (int) result.get("exp");
			long requesttime = System.currentTimeMillis() / 1000;

			valid = result.get("iss").equals(issuer);
			valid = valid && requesttime < expiration;

			return valid;

		} catch (JsonProcessingException e) {
		}

		return false;
	}
}
