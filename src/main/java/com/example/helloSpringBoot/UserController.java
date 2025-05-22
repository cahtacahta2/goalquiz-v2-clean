package com.example.helloSpringBoot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

	@GetMapping("/api/userinfo")
	public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User principal) {
		if (principal == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Giriş yapılmamış");
		}

		Map<String, Object> response = new HashMap<>();
		response.put("name", principal.getAttribute("name"));
		response.put("email", principal.getAttribute("email"));
		return response;
	}
}
