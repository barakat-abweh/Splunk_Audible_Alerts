

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author theblackdevil
 */
public class Listener implements Runnable{
    private int port;
    private String audio;
    private String host;
    public Listener(int port,String audio){
        this.port=port;
        this.audio=audio;
    }
    @Override
    public void run(){
        try {
            Scanner scanner=new Scanner(new File("files/host.conf"));
            this.host=scanner.nextLine();
            ServerSocket SC=new ServerSocket(this.port);
            while(true){
                Socket s=SC.accept();
                String rhost=s.getInetAddress().toString();
                rhost=rhost.substring(1,rhost.length());
                if(rhost.equalsIgnoreCase(this.host)){
                    s.close();
                    SimpleAudioPlayer sap=new SimpleAudioPlayer(this.audio);
                    int timer=5;
                    while(timer>0){
                        sap.play();
                        sleep(1000);
                        timer-=1;
                    }
                    sap.stop();
                }
                else{
                    s.close();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null,"strange connection from : "+rhost);
                }
            }
        } catch (Exception ex) {
            System.err.println("Error");
        }
    }
}