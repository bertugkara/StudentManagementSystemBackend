package com.tubitakyte.studentmanagementsystem.User.Services;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.AssistantDTO;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.DescribeMe.AssistantDescribeMe;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;

import java.util.List;


public interface AssistantService extends UserDTOservice<AssistantDTO> {

   List<AssistantDTO> getAssistantsNotLessonsAttendedToGivenLessonCode(Integer lessonCode);

   List<AssistantDTO> returnListOfAssistantDTO(List<Assistant> AssistantList);

   DataResult<AssistantDescribeMe> describeMe(Integer userID);
}
