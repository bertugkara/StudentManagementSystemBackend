package com.tubitakyte.studentmanagementsystem.Homework.DataAccess;

import com.tubitakyte.studentmanagementsystem.Homework.Entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HomeworkDao extends JpaRepository<Homework,Integer> {

    List<Homework> findAllByLesson_Id(Integer id);

    List<Homework> findAllByCreator_Id(Integer id);

    List<Homework> findAllByAssistant_Id(Integer id);
}
