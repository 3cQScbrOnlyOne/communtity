package org.xzhi.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xzhi.community.dto.QuestionDTO;
import org.xzhi.community.mapper.QuestionMapper;
import org.xzhi.community.mapper.UserMapper;
import org.xzhi.community.model.Question;
import org.xzhi.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

        @Autowired
        private QuestionMapper questionMapper;

        @Autowired
        private UserMapper userMapper;

    public List<QuestionDTO> list() {
            List<Question> questions = questionMapper.list();
            List<QuestionDTO> questionDTOList = new ArrayList<>();
            for (Question question : questions){
                User user = userMapper.findById(question.getCreator());
              QuestionDTO questionDTO =  new QuestionDTO();
                BeanUtils.copyProperties(question,questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        return questionDTOList;
    }
}
