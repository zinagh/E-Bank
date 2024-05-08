package com.mfbank.account_managment.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;

    @JsonProperty("question")
    private String question;

    @JsonProperty("response1")
    private String response1;

    @JsonProperty("response2")
    private String response2;

    @JsonProperty("response3")
    private String response3;

    @JsonProperty("correct")
    private String correct;
}