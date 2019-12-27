/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author majdi
 */
public class Ad {
    
    private int ad_id;
    private String name;
    private Date availability;
    private String description;
    private Date published_at;
    private String image;
    private int user;
    private int likes;
    private String location;

    public Ad() {
    }

    public Ad(int ad_id, String name, Date availability, String description, Date published_at, String image, int user, int likes, String location) {
        this.ad_id = ad_id;
        this.name = name;
        this.availability = availability;
        this.description = description;
        this.published_at = published_at;
        this.image = image;
        this.user = user;
        this.likes = likes;
        this.location = location;
    }

    
    
    
    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAvailability() {
        return availability;
    }

    public void setAvailability(Date availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Date published_at) {
        this.published_at = published_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Ad{" + "ad_id=" + ad_id + ", name=" + name + ", availability=" + availability + ", description=" + description + ", published_at=" + published_at + ", image=" + image + ", user=" + user + ", likes=" + likes + ", location=" + location + '}';
    }
    
    
    
}
