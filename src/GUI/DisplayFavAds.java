/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ad;
import Entity.AdFav;
import Entity.User;
import static GUI.DisplayAds.AVAILABILITY;
import static GUI.DisplayAds.DESCRIPTION;
import static GUI.DisplayAds.ID_AD;
import static GUI.DisplayAds.IMAGE;
import static GUI.DisplayAds.LOCATION;
import static GUI.DisplayAds.PUBLISHED_AT;
import static GUI.DisplayAds.TITRE;
import Service.ServiceAd;
import Service.ServiceAdFav;
import Service.ServiceSession;
import Service.ServiceUser;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
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
import java.util.Date;

/**
 *
 * @author majdi
 */
public class DisplayFavAds extends SideMenuBaseForm {

    private TextField description = new TextField("", "Description");
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};

    Label titre;
    EncodedImage imc;
    Image img;
    ImageViewer imv;

    public static String TITREE;
    public static String AVAILABILITYY;
    public static Date PUBLISHED_ATT;
    public static String DESCRIPTIONN;
    public static String IMAGEE;
    public static String LOCATIONN;
    public static int ID_ADD;
    public DisplayFavAds(Resources res) {

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
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = north(
                        BorderLayout.west(menuButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("            My Favorite Ads", "WelcomeBlue")
                                ));

        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        setupSideMenu(res);

        
                    /* Affichage liste des favoris*/
       
        ServiceAdFav sf = new ServiceAdFav();
        ServiceAd sA = new ServiceAd();
       // System.out.println(idCurrent);
        ArrayList<AdFav> listAdFav = sf.getListFav(1);
        ArrayList<Ad> listAd = sA.getList2();
       for (Ad ad : listAd) {
        for (AdFav adf : listAdFav) {
            System.out.println(ad.getAd_id());
            System.out.println(adf.getAdFav_id());
                
            if(ad.getAd_id()==adf.getIdAd()){
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label titreE = new Label(" " + ad.getName());
                System.out.println("zabour name"+ ad.getName());
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

            Button btnMore = new Button("More");

            btnMore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    TITREE = ad.getName();
                    IMAGEE = ad.getImage();
                    DESCRIPTIONN = ad.getDescription();
                    AVAILABILITYY = ad.getAvailability();
                    PUBLISHED_ATT = ad.getPublished_at();
                    LOCATIONN = ad.getLocation();
                    ID_ADD=ad.getAd_id();
                    DisplayMoreFav ar = new DisplayMoreFav(res);

                    ar.show();
                }
            });
            
            Button btndelete=new Button("delete");
            
            btndelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   ServiceAdFav s=new ServiceAdFav();
                   s.delete(adf.getAdFav_id());
                   DisplayFavAds dd=new DisplayFavAds(res);
                   dd.show();
                }
            });

            c1.add(imv);
            c1.add(titreE);
            c1.add(btnMore);
            c1.add(btndelete);
            c2.add(c1);
            add(c2);
        }
    }} 
        
             /*-------------------------------------*/ 
}

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
