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
public class Service {
    private int service_id,category_id,note;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Service(int service_id, int category_id, int note, String service_name) {
        this.service_id = service_id;
        this.category_id = category_id;
        this.note = note;
        this.service_name = service_name;
    }
    private String service_name;

    public Service(int service_id, int category_id, String service_name) {
        this.service_id = service_id;
        this.category_id = category_id;
        this.service_name = service_name;
        
    }

    public Service() {
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    @Override
    public String toString() {
        return "Service{" + "service_id=" + service_id + ", category_id=" + category_id + ", note=" + note + ", service_name=" + service_name + '}';
    }

   
    
    
}
