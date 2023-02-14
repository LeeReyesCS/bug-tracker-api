package com.adacapstone.bug.tracker.api.Bug;
import com.adacapstone.bug.tracker.api.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bugs")
@Builder
public class Bug {
    @Id
    @SequenceGenerator(name = "bug_sequence",
    sequenceName = "bug_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "bug_sequence")
    private Long bugId;
    @NonNull
    private String priority;
    @Lob
    @Column(
            name = "description",
            length = 512
    )
    @NonNull
    private String description;
    @NonNull
    private String status;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Bug_Table",
            joinColumns = @JoinColumn(name = "bug_id"),
            inverseJoinColumns = @JoinColumn(name ="user_id"))
//    @JsonManagedReference
    private Set<User> assignedDevs;


}
