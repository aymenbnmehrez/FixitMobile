package Service;


import Entity.Category;
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
public class ServiceCategory {
       public ArrayList<Category> getCategory(){
        ArrayList<Category> listcategorie = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/fixitweb1/web/app_dev.php/client/categorymob");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> category = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) category.get("root");

                    for (Map<String, Object> obj : list) {
                        Category cat=new Category();

                        float Id = Float.parseFloat(obj.get("categoryId").toString());
                       
                        cat.setCategory_id((int) Id);
                        cat.setCategory_name(obj.get("name").toString());
                        cat.setCategory_picture(obj.get("image").toString());
                        cat.setCategory_description(obj.get("description").toString());
                     

                        //  ann.setDate_annonce(f.format(time));
                        //task.setMail(obj.get("password").toString());
                       
                        
                        
                        
                        listcategorie.add(cat);
                       // System.out.println(listcategorie);
                        //System.out.println(ann);
                    }

                } catch (IOException ex) {
                }
               

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       
        return listcategorie;
    }
   
}
