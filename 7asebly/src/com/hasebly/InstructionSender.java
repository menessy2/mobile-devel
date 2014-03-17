package com.hasebly;

/**
 * Created by lenovo on 3/17/14.
 */
public class InstructionSender implements Runnable {

    private byte instructionByte;

    public InstructionSender(byte instruction){
        instructionByte = instruction;
    }

    public void executeInstructions(byte[] bytearray) throws InterruptedException {

        int NUMBEROFTHREADS = bytearray.length;
        Runnable[] listofthreads = new Runnable[NUMBEROFTHREADS];
        for (int i=0;i<NUMBEROFTHREADS;i++){
            listofthreads[i] = new InstructionSender(bytearray[i]);
        }
        for(int i=0;i<NUMBEROFTHREADS;i++){
            Thread t = new Thread(listofthreads[i]);
            t.start();
            t.join();
        }
        //System.out.println("Finished");
    }

    synchronized public void executeSingleInstruction(){
        // code to be added for sending the byte instruction to the IDTECH sdk
    }

    public void run(){
        executeSingleInstruction();
    }

    public void instructionResponse(){
        // here should be the function in which the callback of the instruction responses should occur
        // you may need to change the name of the function and add another interface to the class for sure
    }
}
