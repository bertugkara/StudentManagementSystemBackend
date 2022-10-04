package com.tubitakyte.studentmanagementsystem.Rooms.Service;

import com.tubitakyte.studentmanagementsystem.Rooms.RoomDTO.RoomDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateRoomRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;

import java.util.List;

public interface RoomService {

    DataResult<RoomDTO> createRoom(CreateRoomRequest createRoomRequest);

    DataResult<RoomDTO> getOneRoom(int id);

    DataResult<List<RoomDTO>> getAllRooms();


}
