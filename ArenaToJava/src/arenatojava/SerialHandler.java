/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;

/**
 *
 * @author jvzui
 */
/**
 *
 * @author Atulmaharaj
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class SerialHandler implements SerialPortEventListener {

    public SerialPort serialPort;
    public boolean PortOpen = false;
    /**
     * The port we're normally going to use.
     */
    private String PORT_NAMES[];
    /**
     * Milliseconds to block while waiting for port open
     */
    private int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private int DATA_RATE;

    public static BufferedReader input;
    public static OutputStream output;
    
    public SerialHandler(String portName, int dataRate)
    {
        PORT_NAMES = new String[1];
        PORT_NAMES[0] = portName;
        DATA_RATE = dataRate;
    }
    
    public boolean initialize()
    {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements())
        {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            
            for (String portName : PORT_NAMES)
            {
                if (currPortId.getName().equals(portName)) 
                {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null)
        {
            System.out.println("Could not find COM port.");
            return false;
        }

        try
        {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            PortOpen = true;
            return true;
        }
        catch (PortInUseException | UnsupportedCommOperationException | IOException | TooManyListenersException e)
        {
            System.err.println(e.toString());
            return false;
        }
    }

    public void close()
    {
        if (serialPort != null)
        {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent)
    {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        {
            try
            {
                String inputLine = input.readLine();
                System.out.println(inputLine);
            }
            catch (IOException e)
            {
                System.err.println(e.toString());
            }
        }

    }

    public boolean writeData(byte[] data)
    {
        try
        {
            System.out.println("Send Data - Lenght: " + data.length);
            output.write(data);
            return true;
        }
        catch (IOException | NullPointerException e)
        {
            System.out.println("could not write to port:" + e.toString());
            return false;
        }
    }
    
    public boolean writeData(String data)
    {
        try
        {
            System.out.println("Send Data: " + data);
            output.write(data.getBytes());
            return true;
        }
        catch (IOException | NullPointerException e)
        {
            System.out.println("could not write to port:" + e.toString());
            return false;
        }
    }
}
