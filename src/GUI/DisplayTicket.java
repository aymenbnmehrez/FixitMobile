/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ServiceCategoryt;
import Service.TicketService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author user
 */

public class DisplayTicket {
    Form f;
    Container c;
    int i;
      public DisplayTicket() {
     
        f = new Form(BoxLayout.y());
        c=new Container(BoxLayout.y());
//          TicketService p=new TicketService();
//        for(i=0;i<p.getList2().size();i++)
//        {
//            SpanLabel lb = new SpanLabel((p.getList2().get(i).getStatus()));
//            SpanLabel lb1 = new SpanLabel((p.getList2().get(i).getDateTicket()));
//           SpanLabel lb2 = new SpanLabel((p.getList2().get(i).getDescription()));
//              c.add(lb);
//              c.add(lb1);
//             c.add(lb2);
//
//        }
//       f.add(c);
      }
       public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
