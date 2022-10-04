package com.tubitakyte.studentmanagementsystem.Announcements.entity;


import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Admin;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Announcement extends BaseEntity {

    @NotBlank
    @Size(max = 1000)
    private String announcement_text;
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin creator;

    public Announcement(String announcement_text, Admin creator) {
        this.announcement_text = announcement_text;
        this.creator = creator;
    }
}
