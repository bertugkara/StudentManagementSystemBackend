package com.tubitakyte.studentmanagementsystem.common.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTimeRequest {
     @NotBlank
     String startDate;
     @NotBlank
     String endDate;
     @NotBlank
     String day;
}
