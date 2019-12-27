/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Ad;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author majdi
 */
public class ServiceAd {
    
    public ArrayList<Ad> parseListTaskJson(String json) {

        ArrayList<Ad> listTasks = new ArrayList<>();

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
                Ad ad = new Ad();

                float idAd = Float.parseFloat(obj.get("adId").toString());
//                float idUser = Float.parseFloat(obj.get("user").toString()); 
                SimpleDateFormat tempss = new SimpleDateFormat("yyyy-MM-dd");
                String avail = tempss.format(ad.getAvailability());
                String publishedat = tempss.format(ad.getPublished_at());
                
                ad.setAd_id((int) idAd);
              //  ad.setUser((int) idUser);
                ad.setName(obj.get("name").toString());
                ad.setAvailability((Date) obj.get(avail));
                ad.setDescription(obj.get("description").toString());
                ad.setPublished_at((Date) obj.get(publishedat));
                ad.setImage(obj.get("image").toString());
                ad.setLocation(obj.get("location").toString());
                

                System.out.println(ad);
//LinkedHashMap<String,Object> obj1 =  (LinkedHashMap<String,Object>) obj.get("id") ;
//           int pos = 1;
//          
//           //List<String> indexes = new ArrayList<String>(obj1.keySet()); // <== Parse
//           e.setUsername(obj1.get("username").toString());
                listTasks.add(ad);

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
 ArrayList<Ad> listTasks = new ArrayList<>();
 
 public ArrayList<Ad> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixitweb1/web/app_dev.php/client/displayMobileAds");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAd ser = new ServiceAd();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
}
