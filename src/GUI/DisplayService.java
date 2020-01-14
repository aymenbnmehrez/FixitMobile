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
public class DisplayService {
       Form f = new Form();
    SpanLabel lb;
  
   
        EncodedImage imc;
      Image img;
    ImageViewer imv;
      Form affAnn= new Form("Services", BoxLayout.y());
   private Resources theme;
    private ImageViewer bookImg;

  
  

   
    public void DisplayService(int id ) {
        affAnn.getToolbar().addCommandToLeftBar("back", null, eve->{
            DisplayCategory dc = new DisplayCategory();
                dc.DisplayCategory();dc.getF().show();});
        Label l11= new Label();
        l11.setText("MY Services :");
        l11.getStyle().setFgColor(0xb88273);
        affAnn.add(l11);
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
        l1.setText("Name :"+" "+ c.getService_name());
        l1.getStyle().setFgColor(999999);
        affAnn.add(l1);
         Label l2= new Label();
        Label l8 = new Label(" Rating :"+" "+ Integer.toString(c.getNote()));
        l8.getStyle().setFgColor(999999);
        affAnn.add(l8);
        
        
    }
        
      
   

    public Form getF() {
        return affAnn;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
