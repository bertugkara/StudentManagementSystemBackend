package com.tubitakyte.studentmanagementsystem.Announcements.ServicesImpl;

import com.tubitakyte.studentmanagementsystem.Announcements.DTO.AnnouncementDTO;
import com.tubitakyte.studentmanagementsystem.Announcements.DataAccess.AnnouncementDao;
import com.tubitakyte.studentmanagementsystem.Announcements.Services.AnnouncementService;
import com.tubitakyte.studentmanagementsystem.Announcements.entity.Announcement;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.AnnouncementConversion.ConvertAnnouncementListToDtoList;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.AnnouncementConversion.ConvertAnnouncementToAnnouncementDTO;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Admin;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateAnnouncementRequest;
import com.tubitakyte.studentmanagementsystem.utilities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    private final UserDao<Admin> adminUserDao;
    private final AnnouncementDao announcementDao;

    @Override
    public DataResult<AnnouncementDTO> addAnnouncement(CreateAnnouncementRequest request) {
        Announcement announcement=new Announcement(
                request.getAnnouncement_text(),
                adminUserDao.findById(request.getAdminID()).get()
        );
        Announcement test= announcementDao.save(announcement);
        if(test !=null ){
            return new SuccessDataResult<>(getOneAnnouncement(test.getId()).getData(),"Successfully Created Announcement");
        }
        return new ErrorDataResult<>("Error Occured while creating Announcement");
    }

    @Override
    public DataResult<AnnouncementDTO> getOneAnnouncement(Integer announcement_ID) {
        Optional<Announcement> optionalAnnouncement = announcementDao.findById(announcement_ID);
        if(!optionalAnnouncement.isPresent()){
            throw new EntityNotFoundException("No announcement found");
        }
        return new SuccessDataResult<>(ConvertAnnouncementToAnnouncementDTO.convert(optionalAnnouncement.get()).announcementDTO(),"Successfully Fetched");
    }

    @Override
    public DataResult<List<AnnouncementDTO>> getAllAnnouncements() {
        List<Announcement> announcementList=announcementDao.findAll();
        if (announcementList.isEmpty()){
            throw  new EntityNotFoundException("No announcement found");
        }
        return new SuccessDataResult<>(ConvertAnnouncementListToDtoList.convert(announcementList).list(),"Successfully fetched");
    }

    @Override
    public Result deleteById(Integer id) {
        announcementDao.deleteById(id);
        if(announcementDao.findById(id).isPresent()){
            return new ErrorResult("Could not delete");
        }
        return new SuccessResult("Success!");
    }
}
