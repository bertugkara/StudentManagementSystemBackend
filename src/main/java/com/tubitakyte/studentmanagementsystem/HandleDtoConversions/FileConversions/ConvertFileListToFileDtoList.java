package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.FileConversions;

import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DTO.FileDTO;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public record ConvertFileListToFileDtoList(
        List<FileDTO> fileDTOList
) {

    public static ConvertFileListToFileDtoList convert(List<File> fileList) {
        List<FileDTO> tempList = new ArrayList<>();

        fileList.forEach((tempFile) -> {

            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/file/files/")
                    .path(tempFile.getId().toString())
                    .toUriString();

            FileDTO fileDTO = new FileDTO(
                    tempFile.getName(),
                    fileDownloadUri,
                    tempFile.getType(),
                    tempFile.getData().length
            );
            tempList.add(fileDTO);
        });
        return new ConvertFileListToFileDtoList(tempList);
    }
}
