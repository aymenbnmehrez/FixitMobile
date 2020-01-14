/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import Entity.Ticket;
import GUI.DisplayTicket;
import Service.TicketService;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author user
 */
public class TicketForm extends SideMenuBaseForm {

    Form add;
    TicketService TS = new TicketService();

    public TicketForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Jennifer Wilson", "Title"),
                                new Label("UI/UX Designer", "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);

        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, RIGHT, BOTTOM));
        add(new Label("Your Claims ", "TodayTitle"));
        Container c = new Container(BoxLayout.y());
        int i;
        for (Ticket r: TS.getList2()) {//Ticket r : TS.getList2()
            MultiButton mb = new MultiButton(r.getStatus());//r.getStatus()
            mb.setTextLine3(r.getDescription());//r.getDateTicket()
            TS.getList2();
            c.add(mb);
mb.addActionListener((ActionListener) new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     Form Detail =new Form(BoxLayout.y());
                     
                     Label desc=new Label(r.getDescription());
                      Label stat=new Label(r.getStatus());
                       Label date=new Label(r.getDateTicket());
                       
                     Detail.add(desc);
                     Detail.add(stat);
                     Detail.add(date);
                             
                     Detail.show();
                }
            });
        }
        
        fab.addActionListener(e -> {
            add = new Form(new BorderLayout());

            //    Button cameraButton = new Button("Camera");
            FloatingActionButton cameraButton = FloatingActionButton.createFAB(FontImage.MATERIAL_CAMERA);
            cameraButton.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);

            Container centr = new Container(BoxLayout.yCenter());
            Container xc = new Container(BoxLayout.x());
            fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
            TextField a = new TextField("", "Provider");
            TextArea b = new TextArea("", 10, 10);
            Button send = new Button("Send");
            send.addActionListener(ee -> {
                Ticket r=new Ticket();
                r.setDescription(b.getText());
                r.setStatus("Not Treated");
                r.setImage("C:\\Users\\user\\Desktop\\riri.jpg");
               // r.setProvider(a);
               TS.ajoutticket(r);
            });

            cameraButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {

                    String filePath = Capture.capturePhoto();
                    System.out.println("Filepath==" + filePath);
                    try {
                        if (filePath != null) {
                            Image img = Image.createImage(filePath);
                            xc.add(img);
                            xc.revalidate();
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();                        //  Log.sendLogAsync();
                    }
                }
            });

            FloatingActionButton galleryButton = FloatingActionButton.createFAB(FontImage.MATERIAL_PICTURE_IN_PICTURE);
            galleryButton.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);

            galleryButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    Display.getInstance().openGallery((e) -> {
                        if (e != null && e.getSource() != null) {
                            String[] galleryImages = (String[]) e.getSource();
                            try {
                                for (String imageName : galleryImages) {

                                    Image img = Image.createImage(imageName);
                                    img.setImageName(imageName);
                                    xc.add(img);
                                    xc.revalidate();
                                }

                            } catch (IOException err) {

                            }
                        }
                    }, Display.GALLERY_IMAGE_MULTI);
                }
            });

            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        String filePath = (String) ev.getSource();
                        int fileNameIndex = filePath.lastIndexOf("/") + 1;
                        String fileName = filePath.substring(fileNameIndex);

                        Image img = null;
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // MultiList photoList = findPhotoList();
//                    Hashtable tableItem = new Hashtable();
//                    tableItem.put("icon", img.scaled(Display.getInstance().getDisplayHeight() / 10, -1));
//                    tableItem.put("emblem", fileName);
//                    photoList.getModel().addItem(tableItem);
//                    // Do something, add to List
                    }
                }
            }, Display.GALLERY_IMAGE);
            xc.add(galleryButton);
            xc.add(cameraButton);
            centr.add(xc);
            centr.add(a);
            centr.add(b);
            add.add(BorderLayout.CENTER, centr);
            add.add(BorderLayout.SOUTH, send);
            add.show();
        }
        );

        add(c);

        setupSideMenu(res);
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
        new TicketForm(res).show();
    }

}
