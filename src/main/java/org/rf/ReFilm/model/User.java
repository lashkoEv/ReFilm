package org.rf.ReFilm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=5, max=60)
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String username;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonIgnore
    private String password;

    @Email
    private String email;

    private Integer enabled;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval=true)
    @JsonManagedReference
    private Set<Authority> authorities;

    public String getBirthDateFormatted(){
        int year = birthDate.getYear();
        int month = birthDate.getMonthValue();
        int dayOfMonth = birthDate.getDayOfMonth();
        StringBuilder stringBuilder = new StringBuilder();
        if(dayOfMonth < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(dayOfMonth);
        if(month < 10){
            stringBuilder.append(".0");
        }
        stringBuilder.append(month);
        stringBuilder.append(".");
        stringBuilder.append(year);
        return stringBuilder.toString();
    }
}
