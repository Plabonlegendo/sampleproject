package com.example.sampleproject.model;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    @NotNull
    @Column(name = "name")
    @Size(max = 100)
    private String name;

    @NotNull
    @Column(name = "phone_no", unique = true)
    private String phoneNo;

    @NotNull
    @Column(name = "email_id", unique = true)
    private String emailId;

    @NotNull
    @Column(name = "department_name")
    private String departmentName;

    @NotNull
    @Column(name = "role")
    private String role;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "isActive")
    private boolean isActive;

    public Teacher(String name, String phoneNo, String emailId, String departmentName, String role, String password, boolean isActive){
        this.name  = name;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.departmentName = departmentName;
        this.role = role;
        this.password = password;
        this.isActive = isActive;
    }

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List<RequestEvent> requestEventsList;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                CascadeType.PERSIST,
//                CascadeType.MERGE
//            })
//    @JoinTable(name = "Requests",
//            joinColumns = {@JoinColumn(name = "student_id")},
//            inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
//    private Set<Person> invitationRequests = new HashSet<>();

}
