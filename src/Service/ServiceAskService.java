package Service;

import Entity.AskService;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aymen
 */
public class ServiceAskService {

    public ArrayList<AskService> parseListTaskJson(String json) {

        ArrayList<AskService> listTasks = new ArrayList<>();

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
                AskService e = new AskService();

                float id = Float.parseFloat(obj.get("askServiceId").toString());
                float price = Float.parseFloat(obj.get("price").toString());

                e.setAsk_service_id((int) id);
                e.setDuration(obj.get("duration").toString());
                e.setDescription(obj.get("description").toString());
                e.setStarted_at(obj.get("startedAt").toString());
                e.setStatus(obj.get("status").toString());
                e.setPrice((int) price);

                System.out.println(e);
//LinkedHashMap<String,Object> obj1 =  (LinkedHashMap<String,Object>) obj.get("id") ;
//           int pos = 1;
//          
//           //List<String> indexes = new ArrayList<String>(obj1.keySet()); // <== Parse
//           e.setUsername(obj1.get("username").toString());
                listTasks.add(e);

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
 ArrayList<AskService> listTasks = new ArrayList<>();
    
    public ArrayList<AskService> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/client/displayMobile");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAskService ser = new ServiceAskService();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
//jhjhjj
//      public void addAskService(AskService ta) {
//        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
//        String Url = "http://41.226.11.252:11300/tasks/" + ta.getNom() + "/" + ta.getEtat();// création de l'URL
//        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
//
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
//            System.out.println(str);//Affichage de la réponse serveur sur la console
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
//    }
}
