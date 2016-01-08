/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uksw.prir.messenger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Wojciech Pokora
 * @author Jakub Pawlak
 * @author Patryk Szewczyk
 * @author Katarzyna Wiater
 * @author Agnieszka Musiał
 * @author Michał Darkowski
 * 
 */
public class Messanger {

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        startReceiver();
     
        try 
        {
            InetAddress group = InetAddress.getByName("224.0.0.251");
            MulticastSocket s = new MulticastSocket(5353);
            s.joinGroup(group);

            Scanner reader = new Scanner(System.in);
            while(true){
                System.out.println("Write message: ");
                String msg = reader.nextLine();
                DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(),group, 5353);
                s.send(packet);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
     public static void startReceiver() {
        (new Thread() {
            @Override
            public void run() {
                try{
                    InetAddress group = InetAddress.getByName("224.0.0.251");
                    MulticastSocket s = new MulticastSocket(5353);
                    s.joinGroup(group);
  
                    while (true) {
                        byte[] buf = new byte[1000];
                        DatagramPacket recv = new DatagramPacket(buf, buf.length);
                        s.receive(recv);
                        String msg = new String(buf);
                        System.err.println("Received " + recv.getLength() +" bytes from " + recv.getAddress() + " " + msg);
                    }

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }).start();
    }
}
