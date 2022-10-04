package com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class FileDTO {
    private String name;
    private String url;
    private String type;
    private long size;
}
