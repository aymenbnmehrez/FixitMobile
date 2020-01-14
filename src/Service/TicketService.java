/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.AskService;
import Entity.Categoryt;
import Entity.Ticket;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;

import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class TicketService {
    public Ticket getFSubjectById(int id) {
        Ticket an = new Ticket();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/client/ticket/" + Integer.toString(id));
 
        NetworkManager.getInstance().addToQueueAndWait(con);
        return an;
    } 
   
    ArrayList<Ticket> details = new ArrayList<>();

    public ArrayList<Ticket> getDetails2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/admin/ticket/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketService ser = new TicketService();
                details = ser.getDetails(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return details;
    }
    
 public ArrayList<Ticket >parseListTaskJson(String json) {

        ArrayList<Ticket> listTasks = new ArrayList<>();

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
                Ticket e = new Ticket();

                float id = Float.parseFloat(obj.get("id").toString());
               // float price = Float.parseFloat(obj.get("price").toString());
               
                e.setIdTicket((int) id);
                e.setStatus(obj.get("status").toString());
                e.setDescription(obj.get("description").toString());
              //  e.setImage(obj.get("image").toString());
                //e.setStatus(obj.get("status").toString());
              

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
    
    ArrayList<Ticket> listTasks = new ArrayList<>();
    
    public ArrayList<Ticket> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/client/ticket/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketService ser = new TicketService();
                listTasks = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    private ArrayList<Ticket> getDetails(String json) {
        ArrayList<Ticket> details = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));

            Ticket form = new Ticket();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        form.setIdTicket((int) id);
                        System.out.println(form);
                        details.add(form);

            

        } catch (IOException ex) {
        }
     
        return details;

    }
    public void ajoutticket(Ticket r) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/fixit/web/app_dev.php/client/ticket/add?&"+ "&description=" + r.getDescription()+ "&sender=" + r.getSender()+"&provider=" +r.getProvider()+ "&status="+ r.getStatus()+ "&image="+ r.getImage();
        con.setUrl(Url);
        System.out.println("tt");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void delete(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/fixit/web/app_dev.php/client/ticket/delete/" +Integer.toString(id) );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}

