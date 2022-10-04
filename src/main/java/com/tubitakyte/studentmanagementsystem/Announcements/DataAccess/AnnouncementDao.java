package com.tubitakyte.studentmanagementsystem.Announcements.DataAccess;

import com.tubitakyte.studentmanagementsystem.Announcements.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AnnouncementDao extends JpaRepository<Announcement,Integer>  {
}
