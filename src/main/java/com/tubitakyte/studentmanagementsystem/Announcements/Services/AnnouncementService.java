package com.tubitakyte.studentmanagementsystem.Announcements.Services;

import com.tubitakyte.studentmanagementsystem.Announcements.DTO.AnnouncementDTO;
import com.tubitakyte.studentmanagementsystem.common.requests.CreateAnnouncementRequest;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.Result;

import java.util.List;

public interface AnnouncementService {

    DataResult<AnnouncementDTO> addAnnouncement(CreateAnnouncementRequest request);
    DataResult<AnnouncementDTO> getOneAnnouncement(Integer announcement_ID);
    DataResult<List<AnnouncementDTO>> getAllAnnouncements();

    Result deleteById(Integer id);
}
