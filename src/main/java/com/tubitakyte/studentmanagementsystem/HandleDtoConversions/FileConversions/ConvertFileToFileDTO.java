package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.FileConversions;

import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DTO.FileDTO;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public record ConvertFileToFileDTO(
        FileDTO fileDTO
) {

    public static ConvertFileToFileDTO convert(File file) {
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/file/files/")
                .path(file.getId().toString())
                .toUriString();

        return new ConvertFileToFileDTO(
                new FileDTO(
                        file.getName(),
                        fileDownloadUri,
                        file.getType(),
                        file.getData().length
                )
        );
    }
}
