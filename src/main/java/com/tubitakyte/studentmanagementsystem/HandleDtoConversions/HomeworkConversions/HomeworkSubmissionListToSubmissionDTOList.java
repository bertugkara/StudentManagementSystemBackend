package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.HomeworkConversions;

import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.FileConversions.ConvertFileToFileDTO;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertStudentToSimpleStudentDTO;
import com.tubitakyte.studentmanagementsystem.Homework.Entity.HomeworkSubmissions;
import com.tubitakyte.studentmanagementsystem.Homework.HomeworkDTOs.HomeworkSubmissionDTO;

import java.util.ArrayList;
import java.util.List;

public record HomeworkSubmissionListToSubmissionDTOList(
        List<HomeworkSubmissionDTO> homeworkSubmissionsList
) {
    public static HomeworkSubmissionListToSubmissionDTOList convert(List<HomeworkSubmissions> homeworkSubmission){
        List<HomeworkSubmissionDTO> tempList=new ArrayList<>();
        homeworkSubmission.forEach((tempItem)->{
            HomeworkSubmissionDTO homeworkSubmissionDTO=new HomeworkSubmissionDTO(
                    tempItem.getId(),
                    tempItem.getCreatedDate(),
                    ConvertStudentToSimpleStudentDTO.convert(tempItem.getOwner()).simpleStudentDTO(),
                    HomeworkToHomeworkDTO.convertToDTO(tempItem.getHomework()).homeworkDTO(),
                    ConvertFileToFileDTO.convert(tempItem.getFile()).fileDTO()
            );
            tempList.add(homeworkSubmissionDTO);
        });
        return new HomeworkSubmissionListToSubmissionDTOList(tempList);
    }
}
