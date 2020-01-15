/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Ad;
import Entity.AdFav;
import Entity.Post;
import Entity.User;
import GUI.DisplayAds;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author majdi
 */
public class ServiceAdFav {
  User u;  
     public void favorie(int idU) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
//        int idCurrent=ServiceSession.getInstance().getLoggedInUser().getId();
        String Url = "http://localhost/fixit/web/app_dev.php/client/favorite?user=" +idU+"&idAd="+DisplayAds.ID_AD;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
            //Dialog.show("success", "Favoris hasbeen add in your favorites", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
     
     ArrayList<AdFav> listAdFav = new ArrayList<>();
    
    public ArrayList<AdFav> getListFav(int idU){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/client/showFavorite" + "/" + idU);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAdFav ser = new ServiceAdFav();
                listAdFav = ser.parseListTaskJson(new String(con.getResponseData()));
            }       
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAdFav;
    }
     
     
    
    public ArrayList<AdFav> parseListTaskJson(String json) {

        ArrayList<AdFav> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                AdFav favo = new AdFav();

                float adFavId = Float.parseFloat(obj.get("adFavId").toString());
                float user = Float.parseFloat(((Map<String, Object>)obj.get("user")).get("id").toString());
                float idAd = Float.parseFloat(((Map<String, Object>)obj.get("idAd")).get("adId").toString());
               // int user=1;
                favo.setAdFav_id((int) adFavId);
                favo.setUser((int) user);
                favo.setIdAd((int) idAd);

                System.out.println(favo);
                listTasks.add(favo);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listTasks);
        return listTasks;

    }
    
    public void delete(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/client/deleteFavorie/" +id);
            con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    ArrayList<AdFav> listcheck = new ArrayList<>();
    public ArrayList<AdFav> check(int id){
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/client/check/" +id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAdFav ser = new ServiceAdFav();
                listcheck = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcheck;
    }
    
}
