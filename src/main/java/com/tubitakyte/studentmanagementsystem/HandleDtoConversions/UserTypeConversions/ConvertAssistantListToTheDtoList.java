package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.AssistantDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;

import java.util.ArrayList;
import java.util.List;

public record ConvertAssistantListToTheDtoList(
        List<AssistantDTO> assistantDTOList
) {
    public static ConvertAssistantListToTheDtoList convertAssistantListToAssistantDtoList(List<Assistant> assistants){
        List<AssistantDTO> assistantDTOArrayList = new ArrayList<>();
        assistants.forEach((tempStudent) -> {
            AssistantDTO tempStu = ConvertAssistantToAssistantDTO.convertAssistant(tempStudent).assistantDTO();
            assistantDTOArrayList.add(tempStu);
        });
        return new ConvertAssistantListToTheDtoList(assistantDTOArrayList);
    }
}
