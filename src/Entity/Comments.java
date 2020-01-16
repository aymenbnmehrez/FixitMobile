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
public class Comments {
     private String comment;
       private int id;
              private int post_id;
     private String name;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    

    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comments(String comment, int id) {
        this.comment = comment;
        this.id = id;
    }

    public Comments() {
    }

    public Comments(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comments{" + "comment=" + comment + ", id=" + id + '}';
    }

       
}
