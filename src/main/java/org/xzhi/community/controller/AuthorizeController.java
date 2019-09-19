package org.xzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

        @GetMapping("callback")
        public String callback(@RequestParam(name="code") String code,
                               @RequestParam(name="state") String state){
            AccessTokenDto accessTokenDto = new AccessTokenDto();
            accessTokenDto.setClient_id("bd74c0284c60027fe607");
            accessTokenDto.setClient_secret("ebc8bc98044608af493b78b3c32b722a7a9407e6");
            accessTokenDto.setCode(code);
            accessTokenDto.setRedirect_uri("http://localhost:8887/callback");
            accessTokenDto.setState(state);
            String accessToken = githubProvider.getAccessToken(accessTokenDto);
            githubProvider.getAccessToken(new AccessTokenDto());
            GithubUser user = githubProvider.getUser(accessToken);
            System.out.println(user.getName());
            return "index";
        }
}
