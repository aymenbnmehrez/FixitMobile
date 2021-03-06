/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ad;
import Entity.AdFav;
import Entity.User;
import static GUI.DisplayAds.ID_AD;
import Service.ServiceAd;
import Service.ServiceAdFav;
import Service.ServiceSession;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;

import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.north;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import java.util.ArrayList;

/**
 *
 * @author majdi
 */
public class DisplayMore extends SideMenuBaseForm {

    Label titre;
    EncodedImage imc;
    Image img;
    ImageViewer imv;
    public static String location;
    public static User u;
    public DisplayMore(Resources res,User u) {

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("plambing.jpg");
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());

        tb.getUnselectedStyle().setBgImage(tintedImage);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button BackButton = new Button("Back");
        BackButton.setUIID("Title");
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, BackButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("                      " + DisplayAds.TITRE, "WelcomeBlue")
                                ));

        BackButton.addActionListener((s) -> {
            new DisplayAds(res,u).show();
        });

        Button ButtonLocation = new Button("show Location");
        Button ButtonFav = new Button("Add To My Favorite");
        
        ButtonLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent o) {
                location = DisplayAds.LOCATION;
                MapsDemo maCarte = new MapsDemo();
                maCarte.start(res,u);
                System.out.println(location);
            }
        });
        
        ButtonFav.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                ServiceAdFav sf=new ServiceAdFav();
                ArrayList<AdFav> listAdFav = sf.getListFav(u.getId());
                System.out.println(listAdFav);
                 if(sf.check(DisplayAds.ID_AD).isEmpty()){
                    sf.favorie(u.getId());
                    Dialog.show("SuccÃ©s", "Favoris has been add to your favorites", "ok", null);   
                }
                else {

                    Dialog.show("Error", "Favoris existe in your favorites", "ok", null);  
                }
                 
            }
        });

        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        setupSideMenu(res,u);
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        String url = "http://localhost/fixit/web/fixit/public/uploads/" + DisplayAds.IMAGE;
        try {
            imc = EncodedImage.create("/load.png");
        } catch (Exception ex) {
        
        }
        System.out.println("etetetet"+DisplayAds.IMAGE);
        img = URLImage.createToStorage(imc, "" + DisplayAds.IMAGE, url, URLImage.RESIZE_SCALE);
        int displayHeight = Display.getInstance().getDisplayHeight();
        Image scImage = img.scaled(-5, displayHeight / 3);
        imv = new ImageViewer(scImage);

        Label desc = new Label("Description: " + DisplayAds.DESCRIPTION);
        
        SimpleDateFormat dateonly = new SimpleDateFormat("MM/dd/yyyy");
        
        Label availability = new Label("Availability : " + dateonly.format(DisplayAds.AVAILABILITY));
        Label pub = new Label("published at:" + DisplayAds.PUBLISHED_AT);

        C2.add(imv);
        C2.add(desc);
        C2.add(availability);
        C2.add(pub);
        C3.add(ButtonLocation);
        C3.add(ButtonFav);
        C2.add(C3);
        add(C2);      

    }

    @Override
    protected void showOtherForm(Resources res) {
    }
    

    
    
    
    



}
