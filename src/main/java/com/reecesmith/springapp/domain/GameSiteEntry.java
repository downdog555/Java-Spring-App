package com.reecesmith.springapp.domain;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "entries")
public class GameSiteEntry
{
    @Override
    public String toString() {
        return "GameSiteEntry{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", gameTitle='" + gameTitle + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public GameSiteEntry()
    {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "entry_id")
    private Integer id;

    @NotEmpty
    private String user;

    @NotEmpty
    private String gameTitle;

    @NotNull
    private Integer score;

    @NotEmpty
    private String comment;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime modified;

    public GameSiteEntry(@NotEmpty String user, @NotEmpty String gameTitle, @NotEmpty Integer score, @NotEmpty String comment) {
        this.user = user;
        this.gameTitle = gameTitle;
        this.score = score;
        this.comment = comment;
    }
}
