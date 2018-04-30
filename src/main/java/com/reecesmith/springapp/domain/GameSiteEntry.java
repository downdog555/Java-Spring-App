package com.reecesmith.springapp.domain;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Comparator;
import java.util.Date;


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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
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
    @DateTimeFormat(pattern = "HH:mm:ss dd/MM/yyyy")
    private Date created;

    @DateTimeFormat(pattern = "HH:mm:ss dd/MM/yyyy")
    @LastModifiedDate
    private Date modified;

    public GameSiteEntry(@NotEmpty String user, @NotEmpty String gameTitle, @NotEmpty Integer score, @NotEmpty String comment) {
        this.user = user;
        this.gameTitle = gameTitle;
        this.score = score;
        this.comment = comment;


    }


    //comparators to allow for sorting


    public static Comparator<GameSiteEntry> GusernameComparatorASC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            String g1Name = g1.getUser().toUpperCase();
            String g2Name = g2.getUser().toUpperCase();

            //ascending order
            return g1Name.compareTo(g2Name);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};
    public static Comparator<GameSiteEntry> GusernameComparatorDESC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            String g1Name = g1.getUser().toUpperCase();
            String g2Name = g2.getUser().toUpperCase();




            //descending order
            return g2Name.compareTo(g1Name);
        }};
    public static Comparator<GameSiteEntry> GscoreComparatorASC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            int g1score = g1.getScore();
            int g2score = g2.getScore();




            //descending order
            return g1score - g2score;
        }};
    public static Comparator<GameSiteEntry> GscoreComparatorDESC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            int g1score = g1.getScore();
            int g2score = g2.getScore();




            //descending order
            return g2score - g1score;
        }};
    public static Comparator<GameSiteEntry> GgamenameComparatorASC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            String g1Name = g1.getGameTitle().toUpperCase();
            String g2Name = g2.getGameTitle().toUpperCase();

            //ascending order
            return g1Name.compareTo(g2Name);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};
    public static Comparator<GameSiteEntry> GgamenameComparatorDESC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            String g1Name = g1.getGameTitle().toUpperCase();
            String g2Name = g2.getGameTitle().toUpperCase();




            //descending order
            return g2Name.compareTo(g1Name);
        }};
    public static Comparator<GameSiteEntry> GcommentCompartorASC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            String g1Name = g1.getComment().toUpperCase();
            String g2Name = g2.getComment().toUpperCase();

            //ascending order
            return g1Name.compareTo(g2Name);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};
    public static Comparator<GameSiteEntry> GcommentCompartorDESC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            String g1Name = g1.getComment().toUpperCase();
            String g2Name = g2.getComment().toUpperCase();




            //descending order
            return g2Name.compareTo(g1Name);
        }};
    public static Comparator<GameSiteEntry> GcreatedComparatorASC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            Date g1created = g1.getCreated();
            Date g2created = g2.getCreated();

            //ascending order
            return g1created.compareTo(g2created);


        }};
    public static Comparator<GameSiteEntry> GcreatedComparatorDesc = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            Date g1created = g1.getCreated();
            Date g2created = g2.getCreated();

            //ascending order
            return g2created.compareTo(g1created);
        }};
    public static Comparator<GameSiteEntry> GupdatedComparatorASC = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            Date g1updated = g1.getModified();
            Date g2updated = g2.getModified();

            //ascending order
            return g1updated.compareTo(g2updated);


        }};
    public static Comparator<GameSiteEntry> GupdatedComparatorDesc = new Comparator<GameSiteEntry>() {

        public int compare(GameSiteEntry g1, GameSiteEntry g2) {
            Date g1updated = g1.getModified();
            Date g2updated = g2.getModified();

            //ascending order
            return g2updated.compareTo(g1updated);
        }};

}
