package com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.controller;


import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DTO.FileDTO;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.Service.FileService;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = "api/file")
public class FileController {
    private final FileService fileService;

    @GetMapping("/getOneFile")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    DataResult<FileDTO> getOneFile(@RequestParam Integer id) {
        return fileService.getOneFile(id);
    }

    @GetMapping("/getAllFiles")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    DataResult<List<FileDTO>> getAllFiles() {
        return fileService.getAllFiles();
    }

    @GetMapping("/files/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_STUDENT','ROLE_ASSISTANT','ROLE_PROFESSOR')")
    ResponseEntity<byte[]> seeFile(@PathVariable Integer id,@RequestParam Integer creator) {
        return fileService.SeeOneFile(id,creator);
    }
}
