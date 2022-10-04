package com.tubitakyte.studentmanagementsystem.FileSubmissionOperations.entity;

import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class File extends BaseEntity {

    private String name;
    private String type;
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student creator;

    public File(String name, String type, byte[] data, Student creator) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.creator = creator;
    }
}
