/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.servicePost;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;

/**
 *
 * @author Amine
 */
public class Forum {
    
    
    Form f;
    SpanLabel lb;
  
    public Forum() {
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        servicePost p=new servicePost();
        lb.setText(p.getList2().toString());
}
 public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


}