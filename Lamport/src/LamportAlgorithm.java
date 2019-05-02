import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class LamportAlgorithm {
   public static void main (String args []) {
        ActionListener addProcess = new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new Thread (new LamportProcess ())).start();
            }
        };

        JFrame mainFrame = new JFrame ("Lamport");
        mainFrame.setSize (125, 75);
        mainFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

        JButton btnAddProcess = new JButton ("Add Process");
        btnAddProcess.addActionListener(addProcess);
        mainFrame.add(btnAddProcess);

        mainFrame.setVisible (true);

        (new Thread (new LamportServer ())).start();
   }
}

final class LamportServer implements Runnable {
    private int numOfClients = 0;
    ArrayList outputConnections = new ArrayList ();
    private ServerSocket socket;
    private Socket tempSocket;
    private ObjectOutputStream tempObjectOutputStream;
    
    private JFrame serverFrame;
    private JPanel serverPanel;
    private JLabel lblClients;
    private JTextArea txtNotifications;
    private JScrollPane scrollPane;
    
    LamportServer ()  {
            serverFrame = new JFrame ("Mediator");
            serverFrame.setLocationByPlatform (true);
            serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            serverFrame.setSize (225, 200);

            serverPanel = new javax.swing.JPanel();
            serverPanel.setLayout(new java.awt.GridLayout(0, 1));
     
            lblClients = new javax.swing.JLabel();
            lblClients.setFont(new Font ("Times New Roman", 0, 21));
            updateClientCount ();
            
            txtNotifications = new JTextArea (4, 16);
            txtNotifications.setEditable(false);
            scrollPane = new JScrollPane (txtNotifications);
            scrollPane.setSize(180, 150);
            
            serverPanel.add(lblClients);
            serverPanel.add(scrollPane);
            serverFrame.add(serverPanel);
            serverFrame.setVisible(true);
    }
    @Override
    public void run() {
        try {
            socket = new ServerSocket (1234);
            while (true) {
                try {
                    tempSocket = socket.accept();
                    tempObjectOutputStream = new ObjectOutputStream (tempSocket.getOutputStream());
                    tempObjectOutputStream.writeObject(new RegisterMessage (++numOfClients));
                    txtNotifications.append("Registered process number " + numOfClients + "\n");
                    updateClientCount ();
                    outputConnections.add(tempObjectOutputStream);
                    
                    (new Thread (new ServerThread (this, tempSocket))).start();
                } catch (IOException ex) {
                    Logger.getLogger(LamportServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        } catch (IOException ex) {
            Logger.getLogger(LamportServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateClientCount() {
        this.lblClients.setText("Number of Processes: " + numOfClients);
    }
}
class ServerThread implements Runnable {
    LamportServer server;
    Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    ServerThread (LamportServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.inputStream = new ObjectInputStream (socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(LamportServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        CommunicationMessage message;
        while (true) {
            try {
                message = (CommunicationMessage) inputStream.readObject();
                outputStream = (ObjectOutputStream)server.outputConnections.get(message.receivingProcessNumber-1);
                outputStream.writeObject (message);
            } catch (IOException ex) {
                Logger.getLogger(LamportServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LamportServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class LamportProcess implements Runnable {
    private Socket socket;
    private int processNumber;
    private int time = 0;
    
    ObjectInputStream fromServer;
    ObjectOutputStream toServer;
    
    private JFrame processFrame;
    private JTextField txtSendMessageTo;
    private JPanel clockPanel;
    private JPanel clockRatePanel;
    private JButton btnIncrement;
    private JButton btnDecrement;
    private JLabel clock;
    private JPanel messagePanel;
    private JLabel lblSendMessageTo;
    private JButton btnSend;
    private Timer timer;
    private JTextArea notificationArea;

    private ActionListener sendMessage = new ActionListener () {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CommunicationMessage message = new CommunicationMessage (processNumber, time, Integer.parseInt(txtSendMessageTo.getText()));
                toServer.writeObject(message);
            } catch (UnknownHostException ex) {
                Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    };
    
    private ActionListener incrementClockRate = new ActionListener () {

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.setDelay(timer.getDelay()/2);
        }
    };
            
    private ActionListener decrementClockRate = new ActionListener () {

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.setDelay(timer.getDelay()*2);
        }
    };

    LamportProcess () {
    }
    private int initiateConnection () {
        try {
            socket = new Socket ("localhost", 1234);
            toServer = new ObjectOutputStream (socket.getOutputStream());
            fromServer = new ObjectInputStream (socket.getInputStream());

            RegisterMessage message = (RegisterMessage) fromServer.readObject();
            processNumber = message.processNumber;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return processNumber;
    }
    private void Communicate () {
        CommunicationMessage message;
        while (true) {
            try {
                message = (CommunicationMessage) fromServer.readObject();
                notificationArea.append("Got a message from " + message.sendingProcessNumber + " with timestamp " + message.time + "\n");
                if (time <= message.time) {
                    time = message.time;
                    timer.restart();
                    clockTick.actionPerformed(null);
                }
            } catch (IOException ex) {
                Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private ActionListener clockTick = new ActionListener () {

        @Override
        public void actionPerformed(ActionEvent e) {
            clock.setText(String.valueOf(time));
            time++;            
        }
    };
            
    @Override
    public void run() {
        processFrame = new JFrame ("Lamport Process : " + processNumber);
        processFrame.setLocationByPlatform(true);
        processFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        processFrame.setLayout(new FlowLayout ());
        processFrame.setSize (395, 240);
        
        clockPanel = new JPanel (new FlowLayout ());
        clock = new JLabel ();
        clock.setFont(new Font ("Times New Roman", 0, 50));
        clockTick.actionPerformed(null);
        clockPanel.add(clock);
        clockPanel.setVisible(true);
        
        clockRatePanel = new JPanel (new GridLayout (2, 1));
        btnIncrement = new JButton ("+");
        btnDecrement = new JButton ("-");
        btnIncrement.addActionListener(incrementClockRate);
        btnDecrement.addActionListener(decrementClockRate);
        clockRatePanel.add(btnIncrement);
        clockRatePanel.add(btnDecrement);
        clockPanel.add(clockRatePanel);
        processFrame.add(clockPanel);
        
        messagePanel = new JPanel (new GridLayout ());
        lblSendMessageTo = new JLabel ("Send a message to ");
        messagePanel.add(lblSendMessageTo);
        txtSendMessageTo = new JTextField ();
        txtSendMessageTo.addActionListener(sendMessage);
        messagePanel.add (txtSendMessageTo);
        btnSend = new JButton ("Send");
        btnSend.addActionListener(sendMessage);
        messagePanel.add (btnSend);
        processFrame.add(messagePanel);
        
        notificationArea = new javax.swing.JTextArea(5, 32);
        processFrame.add((new JPanel (new GridLayout (1,1))).add(new JScrollPane (notificationArea)));
        
        timer = new Timer (2000, clockTick);
        timer.start();
        
        processFrame.setVisible(true);
        
        processNumber = initiateConnection ();
        processFrame.setTitle("Lamport Process : " + processNumber);        
        Communicate ();
    }
}

interface Message extends Serializable {};

class CommunicationMessage implements Message {
    CommunicationMessage (int sendingProcessNumber, int time, int receivingProcessNumber) {
        this.sendingProcessNumber = sendingProcessNumber;
        this.time = time;
        this.receivingProcessNumber = receivingProcessNumber;
    }
    int sendingProcessNumber;
    int time;
    int receivingProcessNumber;
}

class RegisterMessage implements Message {
    int processNumber = 0;
    RegisterMessage (int ProcessNumber) {
        this.processNumber = ProcessNumber;
    }
    RegisterMessage() {}
}

