package com.tubitakyte.studentmanagementsystem.Rooms.ServiceImpl;

import com.tubitakyte.studentmanagementsystem.Rooms.DataAccess.RoomDao;
import com.tubitakyte.studentmanagementsystem.Rooms.RoomDTO.RoomDTO;
import com.tubitakyte.studentmanagementsystem.Rooms.Service.RoomService;
import com.tubitakyte.studentmanagementsystem.Rooms.entity.Room;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateRoomRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.ErrorDataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomDao roomDao;

    @Override
    public DataResult<RoomDTO> createRoom(CreateRoomRequest createRoomRequest) {
        Room room= createRoomRequest.toEntity();
        roomDao.save(room);
        return new SuccessDataResult<RoomDTO>(getOneRoom(room.getId()).getData(),"Successfully Created Room");
    }

    @Override
    public DataResult<RoomDTO> getOneRoom(int id) {
        Optional<Room> room=roomDao.findById(id);
        if(room.isPresent()) {
            return new DataResult<>(RoomDTO.fromEntity(room.get()), true, "Room with the Id:%d is listed".formatted(id));
        }
        else{ return new ErrorDataResult<>("No Room has been found");}
    }

    @Override
    public DataResult<List<RoomDTO>> getAllRooms() {
        List<Room> roomList=roomDao.findAll();
        List<RoomDTO> roomDTOList=new ArrayList<>();
        if(!roomList.isEmpty()){

           roomDTOList= roomList.stream().map(room -> {
               return RoomDTO.fromEntity(room);
            }).toList();

           return new SuccessDataResult<>(roomDTOList,"Successfully Fetched!");
        }
        return new ErrorDataResult<>("No Room has been founded");

    }
}
