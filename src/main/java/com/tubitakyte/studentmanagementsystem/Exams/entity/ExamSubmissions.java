package com.tubitakyte.studentmanagementsystem.Exams.entity;


import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
public class ExamSubmissions extends BaseEntity {

    @Size(max = 500)
    private String description;

    @Min(0)
    @Max(100)
    private Integer grade; //WILL BE ADDED BY TEACHER LATER

    @ManyToOne
    @JoinColumn(name = "exam_id")
    @NotBlank
    private Exams exam;

    @OneToOne
    @JoinColumn(name = "student_id")
    @NotBlank
    private Student submissioner;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    public ExamSubmissions(String description, Exams exam, Student submissioner, File file) {
        this.description = description;
        this.exam = exam;
        this.submissioner = submissioner;
        this.file = file;
    }
}
