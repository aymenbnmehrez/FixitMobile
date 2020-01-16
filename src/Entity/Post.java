/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Amine
 */
public class Post {
     private int post_id;
          private int id;
private String nom;
   private String title;
   private String content;
   private String post_date;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post(int post_id, String title, String content) {
        this.post_id = post_id;
        this.title = title;
        this.content = content;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

   
    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public Post(int post_id, String title, String content, String post_date) {
        this.post_id = post_id;
        this.title = title;
        this.content = content;
        this.post_date = post_date;
    }

    @Override
    public String toString() {
        return "Post{" + "post_id=" + post_id + ", title=" + title + ", content=" + content + ", post_date=" + post_date + '}';
    }
   
   
}


