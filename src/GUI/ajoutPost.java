/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Post;
import Service.servicePost;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

/**
 *
 * @author bhk
 */
public class ajoutPost {

    Form f;
    TextField ttitle;
    TextField tcontent;
    Button btnajout;

    public ajoutPost() {
        f = new Form("home");
        ttitle = new TextField("","title");
        tcontent = new TextField("","content");
        btnajout = new Button("ajouter");
        f.add(ttitle);
        f.add(tcontent);
        f.add(btnajout);
        btnajout.addActionListener((e) -> {
            servicePost ser = new servicePost();
            Post p = new Post(ttitle.getText(), tcontent.getText());
            ser.ajoutPost(p);
            

        });
       
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTtitle() {
        return ttitle;
    }

    public void setTtitle(TextField ttitle) {
        this.ttitle = ttitle;
    }

    public TextField getTcontent() {
        return tcontent;
    }

    public void setTcontent(TextField tcontent) {
        this.tcontent = tcontent;
    }

  
}
