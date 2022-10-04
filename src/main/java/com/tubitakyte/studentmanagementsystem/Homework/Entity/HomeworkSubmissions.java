package com.tubitakyte.studentmanagementsystem.Homework.Entity;

import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "homework_submissions")
public class HomeworkSubmissions extends BaseEntity {

    @Size(min = 0,max = 500)
    private String description;

    @OneToOne
    @JoinColumn(name = "file_id")
    @NotBlank
    private File file;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @NotBlank
    private Student owner;

    @ManyToOne
    @JoinColumn(name = "homework_id")
    @NotBlank
    private Homework homework;
}
