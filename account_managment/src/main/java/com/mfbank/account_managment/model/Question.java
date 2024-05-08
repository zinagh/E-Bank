package com.mfbank.account_managment.model;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.ComponentScan;



@Setter
@Getter
@Table(name = "question")
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String response1;

    private String response2;

    private String response3;

    private String correct;
    @JsonIgnore




    public Question() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setResponse1(String response1) {
        this.response1 = response1;
    }

    public void setResponse2(String response2) {
        this.response2 = response2;
    }

    public void setResponse3(String response3) {
        this.response3 = response3;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public Question(Long id, String question, String response1, String response2,
                    String response3, String correct) {
        this.id = id;
        this.question = question;
        this.response1 = response1;
        this.response2 = response2;
        this.response3 = response3;
        this.correct = correct;


    }

}

