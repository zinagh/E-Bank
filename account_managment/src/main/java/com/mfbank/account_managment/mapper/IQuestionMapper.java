package com.mfbank.account_managment.mapper;

import com.mfbank.account_managment.dto.BankAccountDto;
import com.mfbank.account_managment.dto.QuestionDto;
import com.mfbank.account_managment.model.BankAccount;
import com.mfbank.account_managment.model.Question;

import java.util.List;

public interface IQuestionMapper {
     QuestionDto toDto(Question question);

     Question toEntity(QuestionDto questionDto);
    public List<QuestionDto> toDtoList(List<Question> questions);
    public List<Question> toEntityList(List<QuestionDto> questionDtos);

}
