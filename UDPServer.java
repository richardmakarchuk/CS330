package com.company;

import java.io.*;
import java.net.*;
import java.util.Random;
public class UDPServer {

        public static void main(String args[]) {
            try{
                DatagramSocket serverSocket = new DatagramSocket(9876);
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                while(true)
                {
                    Random rand = new Random();
                    DatagramPacket receivePacket = new DatagramPacket
                            (receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    String sentence = new String( receivePacket.getData());
                    System.out.println("RECEIVED: " + sentence);
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    String capitalizedSentence = sentence.toUpperCase();
                    if(rand.nextInt(10)<5){
                        sendData = capitalizedSentence.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket
                                (sendData,sendData.length,IPAddress,port);
                        serverSocket.send(sendPacket);
                    }

                }
            }
            catch(IOException e) { System.out.println(e.getMessage()); }
        }
    }

