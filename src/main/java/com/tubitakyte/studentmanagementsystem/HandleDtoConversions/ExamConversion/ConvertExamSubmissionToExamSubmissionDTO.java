package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Exams.entity.ExamSubmissions;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.FileConversions.ConvertFileToFileDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertStudentToStudentDTO;

public record ConvertExamSubmissionToExamSubmissionDTO(
        ExamSubmissionDTO examSubmissionDTO
) {
    public static ConvertExamSubmissionToExamSubmissionDTO convert(ExamSubmissions examSubmissions) {
        return new ConvertExamSubmissionToExamSubmissionDTO(
                new ExamSubmissionDTO(
                        examSubmissions.getId(),
                        examSubmissions.getCreatedDate().toString(),
                        examSubmissions.getGrade(),
                        examSubmissions.getDescription(),
                        ExamToExamDTO.convert(examSubmissions.getExam()).examDTO(),
                        ConvertStudentToStudentDTO.convertStudent(examSubmissions.getSubmissioner()).studentDTO(),
                        ConvertFileToFileDTO.convert(examSubmissions.getFile()).fileDTO()
                )
        );
    }
}
