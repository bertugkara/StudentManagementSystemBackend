package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.AssistantDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;

public record ConvertAssistantToAssistantDTO(
        AssistantDTO assistantDTO
) {
    public static ConvertAssistantToAssistantDTO convertAssistant(Assistant assistant) {

        return new ConvertAssistantToAssistantDTO(
                new AssistantDTO(
                        assistant.getId(),
                        assistant.getFirstName(),
                        assistant.getEmail(),
                        assistant.getUsername(),
                        assistant.getLastName(),
                        assistant.getRoles(),
                        null
                )
        );
    }
}
