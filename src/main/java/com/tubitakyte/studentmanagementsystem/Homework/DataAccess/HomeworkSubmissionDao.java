package com.tubitakyte.studentmanagementsystem.Homework.DataAccess;

import com.tubitakyte.studentmanagementsystem.Homework.Entity.HomeworkSubmissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HomeworkSubmissionDao extends JpaRepository<HomeworkSubmissions,Integer> {

    List<HomeworkSubmissions> findAllByHomework_Id(Integer id);

    List<HomeworkSubmissions> findAllByOwner_Id(Integer id);

}
