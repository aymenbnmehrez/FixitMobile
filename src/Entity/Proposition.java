/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author asus
 */
public class Proposition {
     private int proposition_id,category_id;
    private String proposition_name;

    public Proposition() {
    }

    public Proposition(int proposition_id, int category_id, String proposition_name) {
        this.proposition_id = proposition_id;
        this.category_id = category_id;
        this.proposition_name = proposition_name;
    }

    public int getProposition_id() {
        return proposition_id;
    }

    public void setProposition_id(int proposition_id) {
        this.proposition_id = proposition_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getProposition_name() {
        return proposition_name;
    }

    public void setProposition_name(String proposition_name) {
        this.proposition_name = proposition_name;
    }

    @Override
    public String toString() {
        return "Proposition{" + "proposition_id=" + proposition_id + ", category_id=" + category_id + ", proposition_name=" + proposition_name + '}';
    }
    
}
