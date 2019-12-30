package Service;


import Entity.Category;
import Entity.Service;
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
public class ServiceService {
       public ArrayList<Service> getService(int id ){
        ArrayList<Service> listservice = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/fixitweb1/web/app_dev.php/client/servicemob/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> service = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) service.get("root");

                    for (Map<String, Object> obj : list) {
                        Service ser=new Service();

                        float Id = Float.parseFloat(obj.get("serviceId").toString());
                       
                        ser.setService_id((int) Id);
                        
                        ser.setService_name(obj.get("name").toString());
                        float note = Float.parseFloat(obj.get("note").toString());

                        ser.setNote((int)note);
                     

                        //  ann.setDate_annonce(f.format(time));
                        //task.setMail(obj.get("password").toString());
                       
                        
                        
                        
                        listservice.add(ser);
                       // System.out.println(listcategorie);
                        //System.out.println(ann);
                    }

                } catch (IOException ex) {
                }
               

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       
        return listservice;
    }
   
}
