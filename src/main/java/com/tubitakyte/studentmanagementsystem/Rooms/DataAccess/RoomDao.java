package com.tubitakyte.studentmanagementsystem.Rooms.DataAccess;

import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDao extends JpaRepository<Room,Integer> {

}
