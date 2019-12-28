/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ad;
import Service.ServiceAd;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.components.ImageViewer;
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
import java.util.Date;

/**
 *
 * @author majdi
 */
public class DisplayAds extends SideMenuBaseForm {

    private TextField description = new TextField("", "Description");
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};

    Label titre;
    EncodedImage imc;
    Image img;
    ImageViewer imv;

    public static String TITRE;
    public static Date AVAILABILITY;
    public static String DESCRIPTION;
    public static String IMAGE;

    public DisplayAds(Resources res) {

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

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                        add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("                        Ads", "WelcomeBlue")
                                ));

        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);

        setupSideMenu(res);

        ///////////////////////////////////////////////////////////////////      
        ServiceAd serviceAd = new ServiceAd();
        for (Ad ad : serviceAd.getList2()) {
            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label titreE = new Label("Titre: " + ad.getName());
            String url = "http://localhost/fixitweb1/web/fixit/public/uploads/" + ad.getImage();
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
                                        TITRE = ad.getName();
                    IMAGE = ad.getImage();
                    DESCRIPTION = ad.getDescription();
                    AVAILABILITY =ad.getAvailability();
                    DisplayMore ar =new DisplayMore(res);

       ar.show();
                }
            });

            c1.add(imv);
            c1.add(titreE);
            c1.add(btnMore);
            c2.add(c1);
            add(c2);
        }

        ////////////////////////////////////////////////////////////////////////////////// 
    }

    private Image colorCircle(int color) {
        int size = Display.getInstance().convertToPixels(3);
        Image i = Image.createImage(size, size, 0);
        Graphics g = i.getGraphics();
        g.setColor(color);
        g.fillArc(0, 0, size, size, 0, 360);
        return i;
    }

    private XYMultipleSeriesRenderer createChartMultiRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        for (int color : COLORS) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            r.setFillPoints(false);
            XYSeriesRenderer.FillOutsideLine outline = new XYSeriesRenderer.FillOutsideLine(XYSeriesRenderer.FillOutsideLine.Type.BELOW);
            outline.setColor(color);
            r.addFillOutsideLine(outline);
            r.setLineWidth(5);
        }
        renderer.setPointSize(5f);
        renderer.setLabelsColor(0);
        renderer.setBackgroundColor(0xffffffff);
        renderer.setApplyBackgroundColor(true);
        renderer.setAxesColor(COLORS[0]);

        renderer.setXTitle("");
        renderer.setYTitle("");
        renderer.setAxesColor(0xcccccc);
        renderer.setLabelsColor(0);
        renderer.setXLabels(5);
        renderer.setYLabels(5);
        renderer.setShowGrid(true);

        renderer.setMargins(new int[]{0, 0, 0, 0});
        renderer.setMarginsColor(0xffffff);

        renderer.setShowLegend(false);

        renderer.setXAxisMin(3);
        renderer.setXAxisMax(8);
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(10);

        return renderer;

    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
}
