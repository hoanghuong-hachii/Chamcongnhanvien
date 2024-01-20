
package chamcong;

import java.util.List;
import javax.smartcardio.*;
public class TheNV {
    Card card;
    CardChannel channel;
    CommandAPDU cmndAPDU;
    ResponseAPDU resAPDU;

    public TheNV() {
        connectApplet();
    }

   public boolean connectApplet() {
        try {
            TerminalFactory factory = TerminalFactory.getDefault();
            List<CardTerminal> terminals = factory.terminals().list();
            System.out.println("Terminals: " + terminals);

            CardTerminal terminal = terminals.get(0);

            card = terminal.connect("*");
            System.out.println("card: " + card);

            ATR atr = card.getATR();
            byte[] baAtr = atr.getBytes();
            System.out.print("ATR = 0x");
            for (int i = 0; i < baAtr.length; i++) {
                System.out.printf("%02X ", baAtr[i]);
            }
            channel = card.getBasicChannel();
            System.out.print("Channel= " + channel);
            return true;
        } catch (CardException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void sendAPDUtoApplet(byte[] cmnds,byte[] data){
        try {
            resAPDU = channel.transmit(new CommandAPDU(cmnds[0], cmnds[1], cmnds[2], cmnds[3], data));
        } catch (CardException e) {
            e.printStackTrace();
        }
    }
    public void sendAPDUtoApplet(byte[] cmnds){
        try {
            resAPDU = channel.transmit(new CommandAPDU(cmnds[0], cmnds[1], cmnds[2], cmnds[3]));
        } catch (CardException e) {
            e.printStackTrace();
        }
    }
    public boolean disconnectApplet(){
        try {
            card.disconnect(false);
            return true;
        } catch (CardException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String byteToHex(byte data) {
        StringBuilder result = new StringBuilder();
            result.append(String.format("%02x", data));
        return result.toString();
    }
    public String shorttoHex(short data) {
        StringBuilder result = new StringBuilder();
            result.append(String.format("%02x", data));
        return result.toString();
    } 
    

    
}
