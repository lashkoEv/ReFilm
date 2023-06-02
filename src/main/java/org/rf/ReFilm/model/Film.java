package org.rf.ReFilm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate premiere;

    @NotBlank
    @NotNull
    private String directors;

    @NotBlank
    @NotNull
    private String screenwriters;

    @NotBlank
    @NotNull
    private String actors;

    @PrePersist
    public void toCreate() {
        setPremiere(LocalDate.now());
    }

    public String getPremiereDate(){
        int year = premiere.getYear();
        int month = premiere.getMonthValue();
        int dayOfMonth = premiere.getDayOfMonth();
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
