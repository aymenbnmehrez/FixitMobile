/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.servicePost;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Amine
 */
public class Forum {
    
    
    Form f;
    SpanLabel lb;
  
    public Forum() {
        
       
        f = new Form(BoxLayout.y());
        lb = new SpanLabel("");
        servicePost p = new servicePost();
        for (int i = 0; i < p.getList2().size(); i++) {
           // str+=p.getList2().get(i).getContent();
         
            Container cnt=new Container();
            Container cnt1=new Container();

            Button lb = new Button(p.getList2().get(i).getTitle());
            SpanLabel lbn = new SpanLabel(p.getList2().get(i).getContent());
        cnt.add(lb);
                cnt1.add(lbn);
                f.add(cnt);
                f.add(cnt1);        
                
       // lb.setText(str);
               // f.add(lb);}
        }
}
 public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


}