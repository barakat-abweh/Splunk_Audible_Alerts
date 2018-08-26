/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author theblackdevil
 */
public class Splunk_Audible_Alerts_TCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            new Notification().setVisible(true);
            File file=new File("files/alert.conf");
            Scanner scanner=new Scanner(file);
            ArrayList<Listener> Listeners=new ArrayList();
            while(scanner.hasNextLine()){
                String listenersArr[]=scanner.nextLine().split(" ");
                Listeners.add(new Listener(Integer.parseInt(listenersArr[0]),listenersArr[1]));
            }
            ExecutorService executor= Executors.newFixedThreadPool(Listeners.size());
            for(Listener listener:Listeners){
                executor.execute(listener);
            }
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
    
}
