package com.ztpai.model;


import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "like_b")
    private int likeB;
    @Column(name = "dislike_b")
    private int dislikeB;
    @Column(name = "kcal")
    private int kcal;
    @Column(name = "time")
    private int time;
    @Column(name = "image")
    private String image;
    @Column(name = "link")
    private String link;
    @Column(name = "id_assigned_by")
    private long idAssignedBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikeB() {
        return likeB;
    }

    public void setLikeB(int like) {
        this.likeB = like;
    }

    public int getDislikeB() {
        return dislikeB;
    }

    public void setDislikeB(int dislike) {
        this.dislikeB = dislike;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link.replace("watch?v=", "embed/");
    }

    public long getIdAssignedBy() {
        return idAssignedBy;
    }

    public void setIdAssignedBy(long idAssignedBy) {
        this.idAssignedBy = idAssignedBy;
    }
}
