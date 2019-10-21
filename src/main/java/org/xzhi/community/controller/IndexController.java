package org.xzhi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xzhi.community.dto.QuestionDTO;
import org.xzhi.community.mapper.UserMapper;
import org.xzhi.community.model.User;
import org.xzhi.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (("token").equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;

                }
            }

        List<QuestionDTO> questionList = questionService.list();
        for (QuestionDTO questionDTO : questionList) {
            questionDTO.setDescription("reset");
        }
        model.addAttribute("questions",questionList);
        return "index";

    }

}
