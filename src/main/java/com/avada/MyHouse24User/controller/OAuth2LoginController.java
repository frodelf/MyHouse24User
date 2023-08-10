package com.avada.MyHouse24User.controller;

import com.avada.MyHouse24User.entity.User;
import com.avada.MyHouse24User.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class OAuth2LoginController {
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final UserRepository userRepository;

    @GetMapping("/oauthCallback")
    public String handleOAuth2Callback(OAuth2AuthenticationToken authentication) {
        log.warn("IN handleOAuth2Callback: start");
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName()
        );
        String oauthProvider = authentication.getAuthorizedClientRegistrationId();
        Integer oauthId = authentication.getPrincipal().getAttribute("id");
        if (oauthId != null && oauthProvider != null){
            User existingUser = userRepository.findByOauthIdAndOauthProvider(oauthId, oauthProvider);
            if (existingUser != null) {
                log.info("IN handleOAuth2Callback: user exists. redirecting");
                return "redirect:/user/view";
            }}else log.info("Something went wrong!" +"provider: "+ oauthProvider+"id: "+ oauthId);
        log.info("IN handleOAuth2Callback: registering new user");
        User user = new User();
        user.setOauthProvider(oauthProvider);
        user.setOauthId(oauthId);
        user.setEnabled(true);

        userRepository.save(user);

        return "redirect:/user/view";
    }
    @GetMapping("/secured")
    public String secured() {
        return "redirect:/user/view";
    }
}
