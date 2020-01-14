package GUI;

import Entity.Category;
import Service.ServiceCategory;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
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
      Form affAnn= new Form("Categories", BoxLayout.y());
   private Resources theme;
    private ImageViewer bookImg;
    

  
  

   
    public void DisplayCategory() {
        Label l51= new Label();
        l51.setText("My Categories :");
        affAnn.add(l51);
        l51.getStyle().setFgColor(0xb88273);
                Toolbar.setGlobalToolbar(true);
         TextField searchField = new TextField("", "Toolbar Search");
         Style s = UIManager.getInstance().getComponentStyle("Title");
         searchField.getAllStyles().setAlignment(Component.LEFT);
        affAnn.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        searchField.addDataChangeListener((i1, i2) -> {
            String t = searchField.getText();
            if (t.length() < 1) {
                for (Component cmp : affAnn.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
            } else {
                t = t.toLowerCase();
                for (Component cmp : affAnn.getContentPane()) {
                    String val = null;
                    if (cmp instanceof Label) {
                        val = ((Label) cmp).getText();}
                        
                    else if (cmp instanceof TextArea) {
                        val = ((TextArea) cmp).getText();
                    } else {
                        val = (String) cmp.getPropertyValue("text");
                    }
                    boolean show = val != null && val.toLowerCase().indexOf(t)
                            > -1;
                    cmp.setHidden(!show);
                    cmp.setVisible(show);
                }
            }
            affAnn.getContentPane().animateLayout(250);
        });
        affAnn.getToolbar().addCommandToRightBar( "", searchIcon,(f) -> {
            searchField.startEditingAsync();
        });
        
        
         ArrayList<Category> Category = new ArrayList<>();
         ServiceCategory sa=new ServiceCategory();
         
         Category =sa.getCategory();
        // System.out.println("aaaaaaaaaaaaaaaaa    :"+ID);
         System.out.println("tous afficher"+Category);
         Container gridLay = new Container(new GridLayout(2,2));
         for(Category cat:Category)
            {  
               affichersingleCategory(cat);
            } 
         //DisplayCategory ds = new DisplayCategory();
         //ds.getF();
         
        
}
  public void affichersingleCategory(Category c){
      
      
      
      Label l= new Label();
       l.setText("Name : " +" "+ c.getCategory_name());
        affAnn.add(l);
           String url= "http://localhost/fixit/web/fixit/public/uploads/"+ c.getCategory_picture();
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
        l1.setText("Description :" +" "+ c.getCategory_description());
        affAnn.add(l1);
         
        
         Button service = new Button();
                    service.getAllStyles().setFgColor(ColorUtil.rgb(24, 242, 0));
                    service.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REPLY, service.getUnselectedStyle()));
                    service.addActionListener((ActionListener) (ActionEvent evt) -> {
                       DisplayService dd = new DisplayService();
                       dd.DisplayService(c.getCategory_id());
                       dd.getF().show();

                        
                        
                        
                        

                     
                    });
                    affAnn.add(service);
       
        
        
          //System.out.println("test"+c.getDescription());
        
    }
        
      
   

    public Form getF() {
        return affAnn;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
