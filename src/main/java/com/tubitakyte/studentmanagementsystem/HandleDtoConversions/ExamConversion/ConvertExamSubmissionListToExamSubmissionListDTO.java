package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.ExamConversion;

import com.tubitakyte.studentmanagementsystem.Exams.DTO.ExamSubmissionDTO;
import com.tubitakyte.studentmanagementsystem.Exams.entity.ExamSubmissions;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.FileConversions.ConvertFileToFileDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertStudentToStudentDTO;

import java.util.ArrayList;
import java.util.List;

public record ConvertExamSubmissionListToExamSubmissionListDTO(
        List<ExamSubmissionDTO> examSubmissionsList
) {
    public static ConvertExamSubmissionListToExamSubmissionListDTO convert(List<ExamSubmissions> examSubmissions){
        List<ExamSubmissionDTO> tempList=new ArrayList<>();
        examSubmissions.forEach((tempSubmission)->{
            ExamSubmissionDTO examSubmissionDTO= new ExamSubmissionDTO(
                    tempSubmission.getId(),
                    tempSubmission.getCreatedDate().toString(),
                    tempSubmission.getGrade(),
                    tempSubmission.getDescription(),
                    ExamToExamDTO.convert(tempSubmission.getExam()).examDTO(),
                    ConvertStudentToStudentDTO.convertStudent(tempSubmission.getSubmissioner()).studentDTO(),
                    ConvertFileToFileDTO.convert(tempSubmission.getFile()).fileDTO()
            );
            tempList.add(examSubmissionDTO);
        });
        return new ConvertExamSubmissionListToExamSubmissionListDTO(tempList);
    }

}
