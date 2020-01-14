/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.materialscreens;

import Entity.User;
import GUI.DisplayAds;
import GUI.DisplayCategory;
import Service.ServiceCategory;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res,User u) {
        Image profilePic = res.getImage("photo.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(u.getFirst_name()+" "+u.getLast_name(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Ask For A Service ", FontImage.MATERIAL_DASHBOARD,  e -> new AskServiceForm(res,u).show());
        getToolbar().addMaterialCommandToSideMenu("  My Requests", FontImage.MATERIAL_TRENDING_UP,  e -> new MyRequestForm(res,u).show());
        getToolbar().addMaterialCommandToSideMenu("  Ads ", FontImage.MATERIAL_DASHBOARD,  e -> new DisplayAds(res,u).show());
        getToolbar().addMaterialCommandToSideMenu("  Forum", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> new ClientProfile(res,u).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
         getToolbar().addMaterialCommandToSideMenu("  Category ", FontImage.MATERIAL_DASHBOARD, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                DisplayCategory sd = new DisplayCategory();
                sd.DisplayCategory();
                sd.getF().show();
            }
        }
                 
         );
         getToolbar().addMaterialCommandToSideMenu("Send Claim", FontImage.MATERIAL_WARNING,  e ->new TicketForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
}
