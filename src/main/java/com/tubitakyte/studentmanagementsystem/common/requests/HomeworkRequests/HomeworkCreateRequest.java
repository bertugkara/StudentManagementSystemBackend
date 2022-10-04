package com.tubitakyte.studentmanagementsystem.common.requests.HomeworkRequests;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class HomeworkCreateRequest {

    @NotBlank
    String startDate;
    @NotBlank
    String  endDate;
    @NotBlank
    String startTime;
    @NotBlank
    String endTime;
    @NotBlank
    @Size(max = 500)
    String description;
    @NotBlank
    Integer lesson_id;
    @NotBlank
    Integer responsible_assistant_ID;
    @NotBlank
    Integer creator_id;

}
