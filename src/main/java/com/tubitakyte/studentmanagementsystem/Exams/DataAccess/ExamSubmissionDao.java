package com.tubitakyte.studentmanagementsystem.Exams.DataAccess;

import com.tubitakyte.studentmanagementsystem.Exams.entity.ExamSubmissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamSubmissionDao extends JpaRepository<ExamSubmissions,Integer> {

    List<ExamSubmissions>  findAllByExam_Id(Integer id);
    List<ExamSubmissions>  findAllBySubmissioner_Id(Integer id);

}
