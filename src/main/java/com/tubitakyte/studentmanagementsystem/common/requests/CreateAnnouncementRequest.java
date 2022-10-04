package com.tubitakyte.studentmanagementsystem.common.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnnouncementRequest {

    @NotBlank
    @Size(max = 1000)
    private String announcement_text;

    @NotBlank
    private Integer adminID;
}
