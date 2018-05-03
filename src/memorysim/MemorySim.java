/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorysim;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author SLunsford
 */
public class MemorySim {

    private String reference;
    Scanner keyString = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MemorySim memorySim = new MemorySim();
    
    }
    
    public MemorySim() {
        Scanner sc = new Scanner(System.in);
        int i, j;
        
        
        
        
        j = 0;
        while (j == 0) {
            System.out.println("0 - Exit");
            System.out.println("1 - Read Reference String");
            System.out.println("2 - Generate Reference String");
            System.out.println("3 - Display Current Reference String");
            System.out.println("4 - Simulate FIFO");
            System.out.println("5 - Simulate OPT");
            System.out.println("6 - Simulate LRU");
            System.out.println("7 - Simulate LFU");
            System.out.println("");
            System.out.println("Select option:");
            i = sc.nextInt();
            
            switch(i) {
                case 0:
                    System.out.println("Exiting.");
                    j = 1;
                    break;
                case 1:
                    readString();
                    break;
                case 2:
                    generateString();
                    break;
                case 3:
                    displayString();
                    break;
                case 4:
                    simulateFIFO();
                    break;
                case 5: 
                    simulateOPT();
                    break;
                case 6:
                    simulateLRU();
                    break;
                case 7:
                    simulateLFU();
                    break;
            }
        
        }
    }
    
    private String readString() {
        System.out.println("");
        System.out.println("Please enter a reference string:");
        System.out.println("");
        reference = keyString.nextLine();
        System.out.println("Reference string accepted.");
        System.out.println("");
        return reference;
    }

    private String generateString() {
        int lengthCheck = 0;
        StringBuilder sb = new StringBuilder();
        
        Random rand = new Random();
        System.out.println("Please enter a string length:");
        int stringLength = Integer.parseInt(keyString.nextLine());
        while (lengthCheck < stringLength) {
            int n = rand.nextInt(9);
            sb.append(n);
            reference = sb.toString();
            lengthCheck++;
        }   
        return reference;
    }

    private void displayString() {
        System.out.println("");
        System.out.println("Curent reference string: " + reference);
        System.out.println("");
    }
    
    private void simulateFIFO() {
        int referenceLength = 0;
        int referenceUnit;
        Scanner referenceScanner = new Scanner(reference);
        
        System.out.println("");
        System.out.println("Select number of physical frames:");
        int frameNumber = Integer.parseInt(keyString.nextLine());
        
        while (referenceLength < reference.length()) {
            referenceUnit = referenceScanner.nextInt();
            
        }
        
    }
    
    private void simulateOPT() {
        System.out.println("");
        System.out.println("Select number of physical frames:");
    }
    
    private void simulateLRU() {
        System.out.println("");
        System.out.println("Select number of physical frames:");
    }
    
    private void simulateLFU() {
        System.out.println("");
        System.out.println("Select number of physical frames:");
    }
}
