package com.tubitakyte.studentmanagementsystem.HandleDtoConversions.AnnouncementConversion;

import com.tubitakyte.studentmanagementsystem.Announcements.DTO.AnnouncementDTO;
import com.tubitakyte.studentmanagementsystem.Announcements.entity.Announcement;
import com.tubitakyte.studentmanagementsystem.HandleDtoConversions.UserTypeConversions.ConvertAdminToAdminDTO;

public record ConvertAnnouncementToAnnouncementDTO(
        AnnouncementDTO announcementDTO
) {
    public static ConvertAnnouncementToAnnouncementDTO convert(Announcement announcement){
        return new ConvertAnnouncementToAnnouncementDTO(
                new AnnouncementDTO(
                        announcement.getId(),
                        announcement.getAnnouncement_text(),
                        announcement.getCreatedDate(),
                        ConvertAdminToAdminDTO.convert(announcement.getCreator()).adminDTO()
                )
        );
    }
}
