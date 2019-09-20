package org.xzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xzhi.community.dto.AccessTokenDto;
import org.xzhi.community.dto.GithubUser;
import org.xzhi.community.provider.GithubProvider;

@Controller
public class AuthorizeController {

        @Autowired
        private GithubProvider githubProvider;

        @Value("${github.client.id}")
        private String clientId;

        @Value("${github.client.sercet}")
        private String clientSercet;

        @Value("${github.redirect.uri}")
        private String redirectUri;


        @GetMapping("callback")
        public String callback(@RequestParam(name="code") String code,
                               @RequestParam(name="state") String state){
            AccessTokenDto accessTokenDto = new AccessTokenDto();
            accessTokenDto.setClient_id(clientId);
            accessTokenDto.setClient_secret(clientSercet);
            accessTokenDto.setCode(code);
            accessTokenDto.setRedirect_uri(redirectUri);
            accessTokenDto.setState(state);
            String accessToken = githubProvider.getAccessToken(accessTokenDto);
            githubProvider.getAccessToken(new AccessTokenDto());
            GithubUser user = githubProvider.getUser(accessToken);
            System.out.println(user.getName());
            return "index";
        }
}
