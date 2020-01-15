/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ad;
import Entity.User;
import Service.ServiceAd;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.north;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm_1;
import java.util.Date;

/**
 *
 * @author majdi
 */
public class DisplayAds_Provider extends SideMenuBaseForm_1 {

    private TextField description = new TextField("", "Description");
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};

    Label titre;
    EncodedImage imc;
    Image img;
    ImageViewer imv;

    public static String TITRE;
    public static String AVAILABILITY;
    public static Date PUBLISHED_AT;
    public static String DESCRIPTION;
    public static String IMAGE;
    public static String LOCATION;
    public static int ID_AD;
    
    
    
    public DisplayAds_Provider(Resources res,User u) {

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

        Button favButton = new Button("");
        favButton.setUIID("Title");
        FontImage.setMaterialIcon(favButton, FontImage.MATERIAL_FAVORITE);
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, favButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("                   My Ads", "WelcomeBlue")
                                ));

        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
       // int idCurrent=ServiceSession.getInstance().getLoggedInUser().getId();
//        favButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//           // System.out.println("Current User:"+idCurrent);
//            new DisplayFavAds(res,u).show();
//            }
//        });

        setupSideMenu(res,u);
    
                    /* Affichage liste des annonces*/    
        
        ServiceAd serviceAd = new ServiceAd();
        for (Ad ad : serviceAd.getList3(u.getId())) {
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label titreE = new Label(" " + ad.getName());
            String url = "http://localhost/fixit/web/fixit/public/uploads/" + ad.getImage();
            try {
                imc = EncodedImage.create("/load.png");
            } catch (Exception ex) {

            }

            img = URLImage.createToStorage(imc, "" + ad.getImage(), url, URLImage.RESIZE_SCALE);
            System.out.println(ad.getImage());
            int displayHeight = Display.getInstance().getDisplayHeight();
            Image scImage = img.scaled(-1, displayHeight / 10);
            imv = new ImageViewer(scImage);

            Button btndelete = new Button("delete");

            btndelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     ServiceAd s=new ServiceAd();
                     s.delete(ad.getAd_id());
                }
            });

            c1.add(imv);
            c1.add(titreE);
            c1.add(btndelete);
            c2.add(c1);
            add(c2);
        }

                 /*-----------*/ 
    }

    @Override
    protected void showOtherForm(Resources res) {
    }


 
}
