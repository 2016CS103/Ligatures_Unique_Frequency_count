package com.company;
import org.omg.CORBA.WStringSeqHelper;

import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int j = 1;
        TreeSet<String> UniqueSet = new TreeSet<String>();
        HashMap<String,Integer> hMap = new HashMap<>();
        try {
            FileInputStream myObj = new FileInputStream("file.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("،|و|د|ی|ز|ہ|۔|ا|آ|ر| ");
              //  String[] data = myReader.nextLine().split("د ی م ز ہ۔ ا ر");
               // String[] str = data.split("د ی م ز ہ۔ ا ر");
             //   System.out.println("Number of substrings: "+data.length);
                for (String a : data) {
                   // System.out.println(a + "\t");
                    UniqueSet.add(a);
                }

               // String name = Integer.toString(j);
              //  System.out.println("ligature" + j++);
                for(int i= 0 ; i< data.length ; i++) {
                    if(hMap.containsKey(data[i])) {
                        int count = hMap.get(data[i]);
                        hMap.put(data[i],count+1);
                    } else {
                        hMap.put(data[i],1);
                    }
                }
                File file = new File("Ligatures.txt");

                BufferedWriter bf = null;;

                try{

                    //create new BufferedWriter for the output file
                    bf = new BufferedWriter( new FileWriter(file, true) );
                    String name = Integer.toString(j);
                    bf.newLine();
                    bf.write("ligature Line" + j++);
                    bf.newLine();
                    //iterate map entries
                    for(Map.Entry<String, Integer> entry : hMap.entrySet()){

                        //put key and value separated by a colon
                        bf.write( entry.getValue() + ":" + entry.getKey() +"\t");

                        //new line
                        //bf.newLine();
                    }

                    bf.flush();

                }catch(IOException e){
                    e.printStackTrace();
                }finally{

                    try{
                        //always close the writer
                        bf.close();
                    }catch(Exception e){}
                }


            }
            System.out.println(hMap);
            System.out.println(hMap.size());
            System.out.println(UniqueSet.size());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
