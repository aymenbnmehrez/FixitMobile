/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author majdi
 */
public class AdFav {
    
    private int adFav_id;
    private int user;
    private int idAd;

    public AdFav() {
    }

    public AdFav(int user, int idAd) {
        this.user = user;
        this.idAd = idAd;
    }
    
    

    public AdFav(int adFav_id, int user, int idAd) {
        this.adFav_id = adFav_id;
        this.user = user;
        this.idAd = idAd;
    }

    public int getAdFav_id() {
        return adFav_id;
    }

    public void setAdFav_id(int adFav_id) {
        this.adFav_id = adFav_id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getIdAd() {
        return idAd;
    }

    public void setIdAd(int idAd) {
        this.idAd = idAd;
    }

    @Override
    public String toString() {
        return "AdFav{" + "adFav_id=" + adFav_id + ", user=" + user + ", idAd=" + idAd + '}';
    }
    
    
    
}
