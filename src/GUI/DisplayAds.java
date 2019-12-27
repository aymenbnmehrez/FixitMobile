/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ad;
import Service.ServiceAd;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.Date;

/**
 *
 * @author majdi
 */
public class DisplayAds extends Form {

    Label titre;
    EncodedImage imc;
    Image img;
    ImageViewer imv;
    
    public static String TITRE;
    public static Date AVAILABILITY;
    public static String DESCRIPTION;
    public static String IMAGE;
    

    public DisplayAds() {

        ServiceAd serviceAd = new ServiceAd();

        for (Ad ad : serviceAd.getList2()) {
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label titreE = new Label("Titre: " + ad.getName());
            String url = "http://localhost/fixitweb1/web/fixit/public/uploads" + ad.getImage();
            try {
                imc = EncodedImage.create("/load.png");
            } catch (Exception ex) {

            }
            
            img=URLImage.createToStorage(imc,""+ad.getImage(), url, URLImage.RESIZE_SCALE);
            System.out.println(ad.getImage());
             int displayHeight = Display.getInstance().getDisplayHeight();
        ScaleImageLabel scaleImageLabel = new ScaleImageLabel(img);
        Image scImage = img.scaled(-1, displayHeight / 10);
         imv= new ImageViewer(scImage);
         
         Button btnMore = new Button("More");
         
         btnMore.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                }
            });
         
         c1.add(imv);
         c1.add(titreE);
         c1.add(btnMore);
         c2.add(c1);
         add(c2);
        }
    }
}
