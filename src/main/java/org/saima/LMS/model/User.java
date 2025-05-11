package org.saima.LMS.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




import org.saima.LMS.constants.Role;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String name;
    private String fatherName;
    private String motherName;
    private String phone;
    private String avatarUrl;
//    private Long studentId;
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    public User(String email, String password, Role role, String name, String fatherName,String motherName, String phone,String avatarUrl) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
    }

//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//        updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }
}