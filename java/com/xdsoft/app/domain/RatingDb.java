package com.xdsoft.app.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ratingdb")
public class RatingDb implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "rating")
    private String rating;
    @Column(name = "name")
    private String name;
    @Column(name = "look")
    private String look;

    @Column(name = "date", columnDefinition = "DATE")
    private java.util.Date date;

    @Column(name = "position")
    private String position;



    public RatingDb() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLook() {
        return look;
    }
    public void setLook(String look) {
        this.look = look;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}
