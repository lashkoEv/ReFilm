package org.rf.ReFilm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "posts")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @NotBlank
    @Size(min=5)
    @NotNull
    private String title;

    @NotBlank
    @Size(min=60)
    @NotNull
    private String body;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDate createdTs;

    @PrePersist
    public void toCreate() {
        setCreatedTs(LocalDate.now());
    }

    public String getCreatedDate(){
        int year = createdTs.getYear();
        int month = createdTs.getMonthValue();
        int dayOfMonth = createdTs.getDayOfMonth();
        StringBuilder stringBuilder = new StringBuilder();
        if(dayOfMonth < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(dayOfMonth);
        stringBuilder.append(".");
        if(month < 10){
            stringBuilder.append("0");
        }
        stringBuilder.append(month);
        stringBuilder.append(".");
        stringBuilder.append(year);
        return stringBuilder.toString();
    }
}

