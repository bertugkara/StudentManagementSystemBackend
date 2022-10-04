package com.tubitakyte.studentmanagementsystem.Lesson.DataAccess;

import com.tubitakyte.studentmanagementsystem.Lesson.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonDao extends JpaRepository<Lessons,Integer> {

    List<Lessons> findByStudents_Id(int id);
    List<Lessons> findByAssistants_Id(int id);

    List<Lessons> findByInstructor_Id(int id);

}
