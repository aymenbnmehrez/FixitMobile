/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ServiceCategoryt;
import Service.servicePost;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Amine
 */
public class Categoryt {
    
    Container c;
    Form f;
    SpanLabel lb;
  int i;
    public Categoryt() {
     
        f = new Form(BoxLayout.y());
        ServiceCategoryt p=new ServiceCategoryt();
        for(i=0;i<p.getList2().size();i++)
        {
            SpanLabel lb = new SpanLabel((p.getList2().get(i).getCategory_name()));
              f.add(lb);

        }
}
 public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }


}