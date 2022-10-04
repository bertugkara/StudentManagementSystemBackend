package com.tubitakyte.studentmanagementsystem.Announcements.DTO;


import com.tubitakyte.studentmanagementsystem.User.UserDTO.AdminDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class AnnouncementDTO {

    private Integer id;
    private String announcement_text;
    private Instant createdDate;
    private AdminDTO creator;

}
