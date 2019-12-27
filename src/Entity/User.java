/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Service.ServiceSession;

/**
 *
 * @author aymen
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String roles;
    private String first_name;
    private String last_name;
    private String bio;
    private int phone;
    private String status;

    public User() {
    }

    public User(int id, String username, String email, String password, String roles, String first_name, String last_name, String bio, int phone, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.first_name = first_name;
        this.last_name = last_name;
        this.bio = bio;
        this.phone = phone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles=" + roles + ", first_name=" + first_name + ", last_name=" + last_name + ", bio=" + bio + ", phone=" + phone + ", status=" + status + '}';
    }
     public void ShowUserDebug()
    {
        User u = ServiceSession.getInstance().getLoggedInUser();
        System.out.println("id: "+ u.getId()+"\nUsername: "+
        "\nEmail: "+u.getEmail()+"\npassword: "+u.getPassword()+
                "\nRoles: "+ u.getRoles()
        );
    }
    
}
