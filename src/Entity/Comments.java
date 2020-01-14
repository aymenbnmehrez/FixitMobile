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

    public String getComment() {
        return comment;
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
