package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions;

import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.FileConversions.ConvertFileToFileDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertStudentToSimpleStudentDTO;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.HomeworkSubmissions;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkSubmissionDTO;

public record HomeworkSubmissionToHomeworkSubmissionDTO(
        HomeworkSubmissionDTO homeworkSubmissionDTO
) {
    public static HomeworkSubmissionToHomeworkSubmissionDTO convert(HomeworkSubmissions homeworkSubmission){
        return new HomeworkSubmissionToHomeworkSubmissionDTO(
                new HomeworkSubmissionDTO(
                        homeworkSubmission.getId(),
                        homeworkSubmission.getCreatedDate(),
                        ConvertStudentToSimpleStudentDTO.convert(homeworkSubmission.getOwner()).simpleStudentDTO(),
                        HomeworkToHomeworkDTO.convertToDTO(homeworkSubmission.getHomework()).homeworkDTO(),
                        ConvertFileToFileDTO.convert(homeworkSubmission.getFile()).fileDTO()
                )
        );
    }
}
