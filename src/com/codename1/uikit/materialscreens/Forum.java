/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import Entity.Comments;
import Entity.Post;
import Entity.User;
import Service.ServiceComment;
import Service.servicePost;

/**
 *
 * @author Amine
 */

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//import Entity.Ticket;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author user
 */
public class Forum extends SideMenuBaseForm {

    Form add;
    servicePost TS = new servicePost();
    ServiceComment TC = new ServiceComment();

    public Forum(Resources res,User u) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        
         Image profilePic = res.getImage("photo.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
        
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(u.getFirst_name()+" "+u.getLast_name(), "Title"),
                                new Label("UI/UX Designer", "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );

        
        
        
        
       
        
        
        
        
        
        
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);

        
       
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, BOTTOM));
        add(new Label("Posts ", "TodayTitle"));
        Container c = new Container(BoxLayout.y());
        int i;
        for (Post r: TS.getList2()) {//Ticket r : TS.getList2()
            
           MultiButton mb = new MultiButton(r.getNom());//r.getStatus()
                                   mb.setTextLine3(r.getTitle());//r.getDateTicket()

            mb.setTextLine4(r.getContent());//r.getDateTicket()
                     //   mb.setTextLine3(r.getNom());//r.getDateTicket()

            TS.getList2();
            c.add(mb);
mb.addActionListener((ActionListener) new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    refreshTheme();
                     Form Detail =new Form(BoxLayout.y());
                  
                     Detail.getToolbar().addCommandToLeftBar("Back", null, (l)->{showBack();});
                     
                     Detail.getToolbar().addCommandToRightBar("Delete",null,(e)->{
                       TS.deletepost(r.getPost_id());
                         refreshTheme();
                         Detail.show();
                               
                       
                 });
                     
                     
                     
                     for (Comments a: TC.getList2(r.getPost_id())) {//Ticket r : TS.getList2()
           MultiButton cb = new MultiButton(a.getComment());//r.getStatus()
           
            TC.getList2(r.getPost_id());
            Button Delete=new Button("delete comment");

            
            
            Detail.add(cb);
                                        System.out.println(cb);
 
                     }  
                     
                     
                     
                     

                     Button addcom =new Button("add comment");

                     Detail.add(addcom);
                      addcom.addActionListener(e -> {
                     Form ajoutcomm =new Form(BoxLayout.y());
                                          ajoutcomm.getToolbar().addCommandToLeftBar("Back", null, (l)->{showBack();});

                     TextField tcomment;
    Button btnajoutcom;

                     tcomment = new TextField("","title");
        btnajoutcom = new Button("add");
        ajoutcomm.add(tcomment);
        ajoutcomm.add(btnajoutcom);
        ajoutcomm.show();
        btnajoutcom.addActionListener((s) -> {
            System.out.println(r.getPost_id());
            ServiceComment serr = new ServiceComment();
           // Comments c = new Comments( tcomment.getText(),);
           
           if (tcomment.getText().length()>0)
            serr.ajoutComment(tcomment.getText(),r.getPost_id());
           else 
                       Dialog.show("warning!", "you have an empty field", "OK", "Cancel");

            showBack();
            

        });
       

        }
        );
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     Detail.show();
                }
            });
        }
        
        fab.addActionListener(e -> {
                     Form ajout =new Form(BoxLayout.y());
                                          ajout.getToolbar().addCommandToLeftBar("Back", null, (l)->{showBack();});

                     TextField ttitle;
    TextField tcontent;
    Button btnajout;

                     ttitle = new TextField("","title");
        tcontent = new TextField("","content");
        btnajout = new Button("ajouter");
        ajout.add(ttitle);
        ajout.add(tcontent);
        
        
        
        
        
        
        
        
         Button vocal=new Button("vocal");

        vocal.addActionListener((s) -> {
        
        
        
        Form hi = new Form("Capture", BoxLayout.y());
hi.setToolbar(new Toolbar());
Style x = UIManager.getInstance().getComponentStyle("Title");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MIC, x);

FileSystemStorage fs = FileSystemStorage.getInstance();
String recordingsDir = fs.getAppHomePath() + "recordings/";
fs.mkdir(recordingsDir);
try {
    for(String file : fs.listFiles(recordingsDir)) {
        MultiButton mb = new MultiButton(file.substring(file.lastIndexOf("/") + 1));
        mb.addActionListener((f) -> {
            try {
                Media m = MediaManager.createMedia(recordingsDir + file, false);
                m.play();
            } catch(IOException err) {
                Log.e(err);
            }
        });
        hi.add(mb);
    }

    hi.getToolbar().addCommandToRightBar("", icon, (ev) -> {
        try {
            String file = Capture.captureAudio();
            if(file != null) {
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd-kk-mm");
                String fileName =sd.format(new Date());
                String filePath = recordingsDir + fileName;
                Util.copy(fs.openInputStream(file), fs.openOutputStream(filePath));
                MultiButton mb = new MultiButton(fileName);
                mb.addActionListener((j) -> {
                    try {
                        Media m = MediaManager.createMedia(filePath, false);
                        m.play();
                    } catch(IOException err) {
                        Log.e(err);
                    }
                });
                hi.add(mb);
                hi.revalidate();
            }
        } catch(IOException err) {
            Log.e(err);
        }
    });
} catch(IOException err) {
    Log.e(err);
}
hi.show();
        
        
        
        });
                        ajout.add(vocal);

        
        
        
        
        
        
        ajout.add(btnajout);
        
        
        
        
        
        
        
        
        ajout.show();
        btnajout.addActionListener((s) -> {
            servicePost ser = new servicePost();
            //Post p = new Post(ttitle.getText(), tcontent.getText());
            
                       if ((ttitle.getText().length()>0)&&(tcontent.getText().length()>0))

            ser.ajoutPost(u.getId(),ttitle.getText(),tcontent.getText());
                       else 
                                            Dialog.show("warning!", "you have an empty field", "OK", "Cancel");
      
            showBack();
            

        });
       

        }
        );

        add(c);



        setupSideMenu(res,u);
    }
////
////
////    /*  public Container addItem (Ticket p){
////  Button btn=new Button();  
////Label status=new Label(p.getStatus(),"WelcomeBlue");
////Label desc=new Label(p.getDescription());
////Label categ=new Label("categoryt");
//////Label date=new Label(p.getDateTicket().toString());
////Container c1=new Container(BoxLayout.y());
////Container c2=new Container(BoxLayout.y());
////Container c3=new Container(BoxLayout.x());
////c1.add(status);
//////c1.add(date);
////c2.add(categ);
////c2.add(desc);
////
////c3.add(c1);
////c3.add(c2);
////        btn.addActionListener(e -> {
////            Form details = new Form(BoxLayout.y());
////            Label age = new Label(String.valueOf(p.getStatus()));
////           // Label image = new Label(theme_1.getImage(p.getImage()));
////
////          
////           // details.add(image);
////            details.add(age);
////        });
////        c3.add(btn);
////        c3.setLeadComponent(btn);
////        return c3;
////}*/

    @Override
    protected void showOtherForm(Resources res) {
    }

}
    

