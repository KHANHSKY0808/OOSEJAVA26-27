package com.gpcoder.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {

    public static final byte[] BUFFER = new byte[4096];

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        MulticastSocket socket = null;
        DatagramPacket inPacket = null;
        try {
            // Lấy địa chỉ của nhóm Multicast
            InetAddress address = InetAddress.getByName(MulticastSender.GROUP_ADDRESS);

            // Tạo MulticastSocket gắn với Cổng 8888
            socket = new MulticastSocket(MulticastSender.PORT);

            // Tham gia (Join) vào nhóm Multicast
            socket.joinGroup(address);

            while (true) {
                // Nhận thông điệp từ nhóm
                inPacket = new DatagramPacket(BUFFER, BUFFER.length);
                socket.receive(inPacket);
                
                String msg = new String(BUFFER, 0, inPacket.getLength());
                System.out.println("From " + inPacket.getAddress() + " Msg : " + msg);
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