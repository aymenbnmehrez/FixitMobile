package GUI;

import Service.ServiceCategory;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aymen
 */
public class DisplayCategory {
       Form f;
    SpanLabel lb;
  
    public DisplayCategory() {
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceCategory serviceTask=new ServiceCategory();
        lb.setText(serviceTask.getList2().toString());
        
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
