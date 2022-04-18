package org.rf.ReFilm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

//CREATE TABLE films
//        (
//        id            bigint(20)   NOT NULL AUTO_INCREMENT,
//        name          varchar(100) NOT NULL,
//        premiere      TIMESTAMP    NOT NULL,
//        directors     varchar(255) NOT NULL,
//        screenwriters varchar(255) NOT NULL,
//        actors        varchar(255) NOT NULL,
//        PRIMARY KEY (id)
//        );
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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

    public String getCreatedDate(){
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
