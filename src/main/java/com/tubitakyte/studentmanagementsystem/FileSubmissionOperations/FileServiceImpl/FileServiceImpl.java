package com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.FileServiceImpl;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DTO.FileDTO;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DataAccess.FileDao;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.Service.FileService;
import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.FileConversions.ConvertFileListToFileDtoList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.FileConversions.ConvertFileToFileDTO;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.StudentDao;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class FileServiceImpl implements FileService {

    private final FileDao fileDao;

    private final StudentDao studentDao;

    @Override
    public DataResult<File> addFile(MultipartFile multipartFile,Integer creator) throws IOException {
        Student user= studentDao.findById(creator).get();
        String fileName= StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        File file= new File(fileName,multipartFile.getContentType(),multipartFile.getBytes(),user);
        fileDao.save(file);
        return new SuccessDataResult<>(  file ,"Successfully Created");
    }

    @Override
    public DataResult<FileDTO> getOneFile(Integer id) { //For Spring Operations, Will not service to the Controllers!

        File file=fileDao.findById(id).get();

        if( !fileDao.findById(id).isPresent()){
            throw new EntityNotFoundException("No file found with the associated ID");
        }

        return new SuccessDataResult<>(ConvertFileToFileDTO.convert(file).fileDTO(),"Successfully Fetched");

    }

    @Override
    public DataResult<List<FileDTO>> getAllFiles() {

        List<File> fileList=fileDao.findAll();

        if(fileDao.findAll().isEmpty()){
            throw new EntityNotFoundException("No files found");
        }
        return new SuccessDataResult<>(ConvertFileListToFileDtoList.convert(fileList).fileDTOList());
    }

    public ResponseEntity<byte[]> SeeOneFile(Integer id,Integer creator) {

        File file=fileDao.findById(id).get();
        if(file.getCreator().getId()!=creator){
            throw new RuntimeException("You can not Reach this file!");
        }
        else {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getData());
        }
    }
}
