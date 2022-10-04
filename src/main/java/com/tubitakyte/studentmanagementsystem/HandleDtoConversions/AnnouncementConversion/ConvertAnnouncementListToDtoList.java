package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.AnnouncementConversion;

import com.tubitakyte.studentmanagementsystem.Announcements.DTO.AnnouncementDTO;
import com.tubitakyte.studentmanagementsystem.Announcements.entity.Announcement;

import java.util.ArrayList;
import java.util.List;

public record ConvertAnnouncementListToDtoList(
        List<AnnouncementDTO> list
) {
    public static ConvertAnnouncementListToDtoList convert(List<Announcement> announcementList) {
        List<AnnouncementDTO> announcementDTOList = new ArrayList<>();
        announcementList.forEach((tempAnnouncement) -> {
            announcementDTOList.add( ConvertAnnouncementToAnnouncementDTO.convert(tempAnnouncement).announcementDTO() );
        });
        return new ConvertAnnouncementListToDtoList(announcementDTOList);
    }
}
