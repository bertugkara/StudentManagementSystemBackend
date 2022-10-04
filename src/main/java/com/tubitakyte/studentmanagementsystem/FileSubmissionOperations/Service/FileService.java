package com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.Service;

import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DTO.FileDTO;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    DataResult<File> addFile(MultipartFile multipartFile,Integer creator) throws IOException;

    DataResult<FileDTO> getOneFile(Integer id);

    DataResult<List<FileDTO>> getAllFiles();

    ResponseEntity<byte[]> SeeOneFile(Integer id,Integer creator);

}
