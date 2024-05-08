package com.mfbank.account_managment.mapper;


import com.mfbank.account_managment.dto.QuestionDto;


import com.mfbank.account_managment.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper implements IQuestionMapper{
    @Autowired
    private  ModelMapper modelMapper;

    public QuestionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuestionDto toDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public Question toEntity(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    public List<QuestionDto> toDtoList(List<Question> questions) {
        return questions.stream()
                .map(this::toDto) // Reuse the existing toDto method for each element
                .collect(Collectors.toList());
    }

    public List<Question> toEntityList(List<QuestionDto> questionDtos) {
        return questionDtos.stream()
                .map(this::toEntity) // Reuse the existing toEntity method for each element
                .collect(Collectors.toList());
    }



}
