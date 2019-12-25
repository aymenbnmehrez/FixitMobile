/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author aymen
 */
public class AskService {

    private int ask_service_id;
    private int userId;
    private String duration;
    private String description;
    private String started_at;
    private String status;
    private int price;
    private String service_name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AskService() {
    }

    public AskService(int ask_service_id, int userId, String duration, String started_at, String status, int price, String service_name) {
        this.ask_service_id = ask_service_id;
        this.userId = userId;
        this.duration = duration;
        this.started_at = started_at;
        this.status = status;
        this.price = price;
        this.service_name = service_name;
    }

    public int getAsk_service_id() {
        return ask_service_id;
    }

    public void setAsk_service_id(int ask_service_id) {
        this.ask_service_id = ask_service_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStarted_at() {
        return started_at;
    }

    public void setStarted_at(String started_at) {
        this.started_at = started_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    @Override
    public String toString() {
        return "AskService{" + "ask_service_id=" + ask_service_id + ", userId=" + userId + ", duration=" + duration + ", description=" + description + ", started_at=" + started_at + ", status=" + status + ", price=" + price + ", service_name=" + service_name + '}';
    }

}
