/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ad;
import Entity.AdFav;
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
public class DisplayMoreFav extends SideMenuBaseForm {

    Label titre;
    EncodedImage imc;
    Image img;
    ImageViewer imv;
    public static String locationn;

    public DisplayMoreFav(Resources res) {

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
                                        new Label("                      " + DisplayFavAds.TITREE, "WelcomeBlue")
                                ));

        BackButton.addActionListener((s) -> {
            new DisplayFavAds(res).show();
        });

        Button ButtonLocation = new Button("Delete");
        
        ButtonLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent o) {
                locationn = DisplayFavAds.LOCATIONN;
                System.out.println(locationn);
                MapsDemo maCartee = new MapsDemo();
                maCartee.start();
                System.out.println(locationn);
            }
        });
        

        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        setupSideMenu(res);
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        String url = "http://localhost/fixit/web/fixit/public/uploads/" + DisplayFavAds.IMAGEE;
        try {
            imc = EncodedImage.create("/load.png");
        } catch (Exception ex) {
        
        }
        System.out.println("etetetet"+DisplayFavAds.IMAGEE);
        img = URLImage.createToStorage(imc, "" + DisplayFavAds.IMAGEE, url, URLImage.RESIZE_SCALE);
        int displayHeight = Display.getInstance().getDisplayHeight();
        Image scImage = img.scaled(-5, displayHeight / 3);
        imv = new ImageViewer(scImage);

        Label desc = new Label("Description: " + DisplayFavAds.DESCRIPTIONN);
        
        SimpleDateFormat dateonly = new SimpleDateFormat("MM/dd/yyyy");
        
        Label availability = new Label("Availability : " + dateonly.format(DisplayFavAds.AVAILABILITYY));
        Label pub = new Label("published at:" + DisplayFavAds.PUBLISHED_ATT);

        C2.add(imv);
        C2.add(desc);
        C2.add(availability);
        C2.add(pub);
        C3.add(ButtonLocation);
        C2.add(C3);
        add(C2);      

    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

}