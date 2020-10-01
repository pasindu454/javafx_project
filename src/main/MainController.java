package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    TextField path;
    @FXML
    ProgressBar pBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void filter(ActionEvent event){
        if(!path.getText().equals(null)) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String s = path.getText();
                    List<String> list = scan(s);
                    getDetails(list);

                }
            });
            thread.start();
            pBar.setProgress(-1);
        }
    }
    private void getDetails(List<String> list){
        Document document = null;
        List<Contact> contacts = new ArrayList<>();
            for(int i=0;i<list.size();i++){

                try {
                   // System.out.println(list.get(i));
                    document = Jsoup.connect(list.get(i)).get();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                String name = document.select(".contact-name--m97Sb").text();
                String number = document.select(".number--12hbs").text();
                //System.out.println(document.select(".poster-details--2XBt1"));
                System.out.println(name+" "+number);
                contacts.add(new Contact(name, number));
            }
            //printList(contacts);
    }
   /*private void printList(List<Contact> contacts){
        for(Contact c : contacts){
            System.out.println(c.name+" "+c.num);
        }
   }*/
    private List<String> scan(String link){
        Document doc = null;
        try{
              doc = Jsoup.connect(link).get();
        }catch (IOException e){
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
      for(Element e:doc.select(".list--3NxGO li a")){
           String l = e.attr("href");
                   if(!l.equals("/en/promotions")){
           list.add("https://ikman.lk"+l);
                   }
      }
      return list;
    }



}
