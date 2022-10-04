package com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.DataAccess;

import com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FileDao extends JpaRepository<File,Integer> {
}
