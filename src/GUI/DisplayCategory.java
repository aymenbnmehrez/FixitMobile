package GUI;

import Entity.Category;
import Service.ServiceCategory;
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
public class DisplayCategory {
       Form f = new Form();
    SpanLabel lb;
  
   
        EncodedImage imc;
      Image img;
    ImageViewer imv;
      Form affAnn= new Form();
   private Resources theme;
    private ImageViewer bookImg;

  
  

   
    public void DisplayCategory() {
         ArrayList<Category> Category = new ArrayList<>();
         ServiceCategory sa=new ServiceCategory();
         
         Category =sa.getCategory();
        // System.out.println("aaaaaaaaaaaaaaaaa    :"+ID);
         System.out.println("tous afficher"+Category);
         Container gridLay = new Container(new GridLayout(2,2));
         for(Category cat:Category)
            {  
               affichersingleAnnonce(cat);
            } 
         //DisplayCategory ds = new DisplayCategory();
         //ds.getF();
         
        
}
  public void affichersingleAnnonce(Category c){
           String url="http://localhost/fixitweb1/fixit/public/uploads/"+c.getCategory_picture();
          try {
                   imc = EncodedImage.create("/load.png");
               } catch (Exception ex) {
                 
               }
          
      img=URLImage.createToStorage(imc,""+c.getCategory_picture(), url, URLImage.RESIZE_SCALE);
             int displayHeight = Display.getInstance().getDisplayHeight();
        ScaleImageLabel scaleImageLabel = new ScaleImageLabel(img);
        Image scImage = img.scaled(-1, displayHeight / 5);
         imv= new ImageViewer(scImage);
        
        affAnn.add(imv); 
         Label l1= new Label();
        l1.setText(c.getCategory_description());
        affAnn.add(l1);
         Label l2= new Label();
        l2.setText(c.getCategory_name());
        affAnn.add(l2);
        Label l= new Label();
        l.setText(c.getCategory_picture());
        affAnn.add(l);
         Button delete = new Button();
                    delete.getAllStyles().setFgColor(ColorUtil.rgb(24, 242, 0));
                    delete.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REPLY, delete.getUnselectedStyle()));
                    delete.addActionListener((ActionListener) (ActionEvent evt) -> {
                       DisplayService dd = new DisplayService();
                       dd.DisplayService(c.getCategory_id());
                       dd.getF().show();

                        
                        
                        
                        

                     
                    });
                    affAnn.add(delete);
       
        
        
          //System.out.println("test"+c.getDescription());
        
    }
        
      
   

    public Form getF() {
        return affAnn;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
