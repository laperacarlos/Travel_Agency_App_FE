package com.kodilla.travelagencyfe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ComplaintAnswer {
    private Long id;
    private Complaint complaint;
    private String answer;
    private LocalDateTime creationDate;

    public ComplaintAnswer(Complaint complaint, String answer, LocalDateTime creationDate) {
        this.complaint = complaint;
        this.answer = answer;
        this.creationDate = creationDate;
    }
}
