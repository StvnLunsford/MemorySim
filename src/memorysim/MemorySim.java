/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorysim;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
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
    public static void main(String[] args) throws IOException {
        //instantiates memorySim to allow for use of the methods below
        MemorySim memorySim = new MemorySim();
    
    }
    
    public MemorySim() throws IOException {
        Scanner sc = new Scanner(System.in);
        int i, j;
        
        
        
        //creates the list for the UI; when switch case 0 is hit the program ends
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
    
    //reads the string from keyboard input
    private String readString() {
        System.out.println("");
        System.out.println("Please enter a reference string:");
        System.out.println("");
        reference = keyString.nextLine();
        System.out.println("Reference string accepted.");
        System.out.println("");
        return reference;
    }

    //generates a random string for use
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

    //displays the current string in use
    private void displayString() {
        System.out.println("");
        System.out.println("Curent reference string: " + reference);
        System.out.println("");
    }
    
    //simulates FIFO with the selected String
    private void simulateFIFO() throws IOException {
        int referenceLength = 0;
        char referenceUnit;
        int step = 0;
        int pageFault = 0;

        System.out.println("");
        System.out.println("Select number of physical frames:");
        int frameNumber = Integer.parseInt(keyString.nextLine());
        LinkedList frames = new LinkedList();
        
        while (referenceLength < reference.length()) {
            referenceUnit = reference.charAt(referenceLength); 
            //skips the element if it's already in the list
            if (frames.contains(referenceUnit)) {
                System.out.println("No fault!");
            //adds the first elements to the list
            } else if (step < frameNumber) {                
                frames.addFirst(referenceUnit);
                System.out.println("No fault!");
                step++;
            //removes the oldest element and appends the new one
            } else {
                frames.removeLast();
                frames.addFirst(referenceUnit);
                pageFault++;
                step++;
            } 
            System.out.println(Arrays.toString(frames.toArray()));
            
            referenceLength++;
        }
        System.out.println("Total page faults: " + pageFault);
        System.out.println("");
    }
    
    //simulates OPT with the selected String
    private void simulateOPT() {
        int referenceLength = 0;
        char referenceUnit;
        int checkLength = 0;
        char checkUnit;
        int scanUnit = 0;
        int step = 0;
        int pageFault = 0;
        boolean refExists = false;
        LinkedList checkList = new LinkedList();
        
        System.out.println("");
        System.out.println("Select number of physical frames:");
        int frameNumber = Integer.parseInt(keyString.nextLine());
        LinkedList frames = new LinkedList();
        
        while (checkLength < reference.length()){
            checkUnit = reference.charAt(checkLength);
            checkList.add(checkUnit);
            checkLength++;
        }
        
        while (referenceLength < reference.length()) {
            referenceUnit = reference.charAt(referenceLength); 
            //skips the element if it's already in the list
            if (frames.contains(referenceUnit)) {
                System.out.println("No fault!");
                checkList.removeFirst();
            //fills in the first elements up to the no. of frames
            } else if (step < frameNumber) {                
                frames.addFirst(referenceUnit);
                System.out.println("No fault!");
                checkList.removeFirst();
                step++;
            //checks for further use of the element and replaces it 
            } else if (scanUnit < frameNumber){
                while (refExists == false && scanUnit < frameNumber) { 
                    char c = (char) frames.get(scanUnit);
                    if (frames.contains(referenceUnit)) {
                System.out.println("No fault!");
                checkList.removeFirst();
                    } else if (checkList.contains(c)) {
                        scanUnit++;
                    } else {
                        int position = frames.indexOf(c);
                        frames.remove(position);
                        frames.add(position, referenceUnit);
                        checkList.removeFirst();
                        pageFault++;
                        scanUnit = 0;
                        refExists = true;
                    }
                } 
                refExists = false;
            } else {
                frames.removeLast();
                frames.addFirst(referenceUnit);
                pageFault++;
                step++;  
                }
            System.out.println(Arrays.toString(frames.toArray()));
            
            referenceLength++;
        }
        System.out.println("Total page faults: " + pageFault);
        System.out.println("");
    }
          
    //simulates LRU with the selected String
    private void simulateLRU() {
        int referenceLength = 0;
        char referenceUnit;
        int checkLength = 0;
        char checkUnit;
        int scanUnit = 0;
        int step = 0;
        int pageFault = 0;
        LinkedList checkList = new LinkedList();
        
        System.out.println("");
        System.out.println("Select number of physical frames:");
        int frameNumber = Integer.parseInt(keyString.nextLine());
        LinkedList frames = new LinkedList();
        
        while (checkLength < reference.length()){
            checkUnit = reference.charAt(checkLength);
            checkList.add(checkUnit);
            checkLength++;
        }
        
        
        while (referenceLength < reference.length()) {
            referenceUnit = reference.charAt(referenceLength); 
            //skips the element if it's already in the list
            if (frames.contains(referenceUnit)) {
                System.out.println("No fault!");
            //fills in the first elements up to the no. of frames
            } else if (step < frameNumber) {                
                frames.addFirst(referenceUnit);
                System.out.println("No fault!");
                step++;
            //once the frames are filled, compares indices to find oldest element    
            } else {
                int i = 0;
                int compWin = 0;
                while (i < frameNumber - 1) {
                    char c1 = (char) frames.get(compWin); 
                    scanUnit = checkList.indexOf(c1);
                    char c2 = (char) frames.get(i+1);
                    int scanUnit2 = checkList.indexOf(c2);
                    if (scanUnit <= scanUnit2) {
                        compWin = frames.indexOf(c1);
                    } else {
                        compWin = frames.indexOf(c2);
                    }
                    i++;
                }

                frames.remove(compWin);
                frames.add(compWin, referenceUnit);
                pageFault++;
            }
            System.out.println(Arrays.toString(frames.toArray()));
            referenceLength++;
        }
        System.out.println("Total page faults: " + pageFault);
        System.out.println("");
    }
    
    //simulates LFU with the selected String
    private void simulateLFU() {
        int referenceLength = 0;
        char referenceUnit;
        int checkLength = 0;
        char checkUnit;
        int scanUnit = 0;
        int step = 0;
        int pageFault = 0;
        LinkedList checkList = new LinkedList();

        System.out.println("");
        System.out.println("Select number of physical frames:");
        int frameNumber = Integer.parseInt(keyString.nextLine());
        LinkedList frames = new LinkedList();
        
        while (checkLength < reference.length()){
            checkUnit = reference.charAt(checkLength);
            checkList.add(checkUnit);
            checkLength++;
        }
        
        while (referenceLength < reference.length()) {
            referenceUnit = reference.charAt(referenceLength); 
            //skips the element if it's already in the list
            if (frames.contains(referenceUnit)) {
                System.out.println("No fault!");
            //fills in the first elements up to the no. of frames
            } else if (step < frameNumber) {                
                frames.addFirst(referenceUnit);
                System.out.println("No fault!");
                step++;
            //once frame is filled, compares number of occurrences
            } else {
                int i = 0;
                int compWin = 0;
                while (i < frameNumber - 1) {
                    char c1 = (char) frames.get(compWin); 
                    scanUnit = checkList.indexOf(c1);
                    char c2 = (char) frames.get(i+1);
                    int scanUnit2 = checkList.indexOf(c2);
                    if (scanUnit <= scanUnit2) {
                        compWin = frames.indexOf(c1);
                    } else {
                        compWin = frames.indexOf(c2);
                    }
                    i++;
                }

                frames.remove(compWin);
                frames.add(referenceUnit);
                pageFault++;
            }
            System.out.println(Arrays.toString(frames.toArray()));
            referenceLength++;
        }
        System.out.println("Total page faults: " + pageFault);
        System.out.println("");
    }
}
