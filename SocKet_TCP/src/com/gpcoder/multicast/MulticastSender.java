package com.gpcoder.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastSender {

    public static final String GROUP_ADDRESS = "224.0.0.1";
    public static final int PORT = 8888;

    public static void main(String[] args) throws InterruptedException {
        DatagramSocket socket = null;
        try {
            // Lấy địa chỉ IP Multicast (Lớp D: 224.0.0.0 - 239.255.255.255)
            InetAddress address = InetAddress.getByName(GROUP_ADDRESS);

            // Tạo DatagramSocket
            socket = new DatagramSocket();

            DatagramPacket outPacket = null;
            long counter = 0;
            while (true) {
                String msg = "Sent message No. " + counter;
                counter++;
                
                // Tạo gói tin chứa dữ liệu và địa chỉ nhóm Multicast
                outPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, PORT);
                socket.send(outPacket); // Gửi gói tin đi
                
                System.out.println("Server sent packet with msg: " + msg);
                Thread.sleep(1000); // Nghỉ 1 giây trước khi gửi câu tiếp theo
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}