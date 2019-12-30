package GUI;

import Entity.Category;
import Entity.Service;
import Service.ServiceCategory;
import Service.ServiceService;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aymen
 */
public class DisplayService {
       Form f = new Form();
    SpanLabel lb;
  
   
        EncodedImage imc;
      Image img;
    ImageViewer imv;
      Form affAnn= new Form();
   private Resources theme;
    private ImageViewer bookImg;

  
  

   
    public void DisplayService(int id ) {
         ArrayList<Service> Service = new ArrayList<>();
         ServiceService sa=new ServiceService();
         
         Service =sa.getService(id);
        // System.out.println("aaaaaaaaaaaaaaaaa    :"+ID);
        // System.out.println("tous afficher"+Category);
         Container gridLay = new Container(new GridLayout(2,2));
         for(Service cat:Service)
            {  
               affichersingleAnnonce(cat);
            } 
         //DisplayCategory ds = new DisplayCategory();
         //ds.getF();
         
        
}
  public void affichersingleAnnonce(Service c){
         
        
        //affAnn.add(imv); 
         Label l1= new Label();
        l1.setText(c.getService_name());
        affAnn.add(l1);
         Label l2= new Label();
        Label l8 = new Label(Integer.toString(c.getNote()));
        affAnn.add(l8);
        
        
    }
        
      
   

    public Form getF() {
        return affAnn;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
