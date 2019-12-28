/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.north;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;

/**
 *
 * @author majdi
 */
public class DisplayMore extends SideMenuBaseForm  {
    
    Label titre;
    EncodedImage imc;
    Image img;
    ImageViewer imv;
    
    public DisplayMore(Resources res) {
        
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
//        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
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
        
        BackButton.addActionListener((s)->{
            new DisplayAds(res).show();
        });

        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        setupSideMenu(res);
                    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                      
              //  Label titreAd = new Label("Titre: " + DisplayAds.TITRE);
                 String url="http://localhost/fixitweb1/web/fixit/public/uploads/"+DisplayAds.IMAGE;
                 try {
                   imc = EncodedImage.create("/load.png");
               } catch (Exception ex) {
                 
               }
            System.out.println(DisplayAds.IMAGE);
      img=URLImage.createToStorage(imc,""+DisplayAds.IMAGE, url, URLImage.RESIZE_SCALE);
            int displayHeight = Display.getInstance().getDisplayHeight();
       //ScaleImageLabel scaleImageLabel = new ScaleImageLabel(img);
       Image scImage = img.scaled(-1, displayHeight / 10);
        imv= new ImageViewer(scImage);
         
        
                            Label desc = new Label("Description: "+ DisplayAds.DESCRIPTION);
        System.out.println(DisplayAds.DESCRIPTION);
                                    Label availability = new Label("Availability : " + DisplayAds.AVAILABILITY);

         
//                Label animateurE = new Label("Animateur: " + ev.getAnimateurEvent());
//                Label lieuE = new Label("Lieu: " + ev.getLieuEvent());
//                Label lienE = new Label("Lien: " + ev.getLienEvent());
//                Label datedebE = new Label("Date debut: " + ev.getDateEvent());
//                Label datefinE = new Label("Date fin: " + ev.getDatefinEvent());
//                
//                Label nbpE = new Label("nbplace: " + ev.getNbplaceEvent());
//                Label fraisE = new Label("frais: " + ev.getFraisEvent());
//
//                
//                Label descriptionE = new Label("Description: " + ev.getDescriptionEvent()); 
//
//            
//                Label afficheE = new Label("Affiche: " + ev.getAfficheEvent());




                                    
          
   // C1.add(titreAd);
    C1.add(imv);
    C1.add(desc);
    C1.add(availability);
    add(C1);
   // C1.add(imv);
          

//    C1.add(animateurE);
//    C1.add(lieuE);
//    C1.add(lienE);
//    C1.add(datedebE);
//   C1.add(datefinE);
//    C1.add(nbpE);
//    C1.add(fraisE);
//C1.add(descriptionE);
//    C1.add(afficheE);
           
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }


}
