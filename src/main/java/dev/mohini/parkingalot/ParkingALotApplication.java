package dev.mohini.parkingalot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@SpringBootApplication
public class ParkingALotApplication {

//    public static void main(String[] args) {
  //      SpringApplication.run(ParkingALotApplication.class, args);
   // }

//}
    public static HashMap<String, Integer> parking=new HashMap<>();
    public static PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) {

        // SpringApplication.run(ParkinglotApplication.class, args);
        try
        {
            File file = new File("src/main/resources/Command.txt");


            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                // System.out.println(st);

                String[] commands=st.split(" ");
                getResponse(commands);
            }
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void getResponse(String[] args)
    {
        if(args[0].equals("create_parking_lot"))
        {
            int n=Integer.parseInt(args[1]);

            //add all capacity in priorityQueue
            pq=new PriorityQueue<>(6);
            System.out.println("Created parking lot with "+n+" slots");
            for(int i=1;i<=n;i++) {
                pq.add(i);
            }
            System.out.println(pq.size());
        }

        else
        if(args[0].equals("park"))
        {
            String carNumber=args[1];
            if(pq.isEmpty())
                System.out.println("Sorry, parking lot is full");
            else {
                int slotNumber = pq.poll();
                parking.put(carNumber, slotNumber);
                System.out.println("Allocated slot number: "+slotNumber);
            }
        }
        else
        if(args[0].equals("leave"))
        {
            String carNumber=args[1];
            int hours=Integer.parseInt(args[2]);
            if(parking.get(carNumber)==null)
            {
                System.out.println("Registration number "+carNumber+" not found");
                return ;
            }

            int slot=parking.get(carNumber);
            int charge=10;
            if(hours>2)
                charge+=(hours-2)*10;
            parking.remove(carNumber);
            pq.add(slot);
            System.out.println("Registration number "+carNumber+
                    " with Slot Number "+ slot
                    +" is free with Charge "+charge);
        }
        else
        if(args[0].equals("status"))
        {
            System.out.println("Slot No."+"\t"+"Registration No.");
            HashMap<String, Integer> sortedNumber=sortByValue(parking);

            for (Map.Entry<String, Integer> details : sortedNumber.entrySet()) {
                System.out.println(details.getValue()+"\t"+details.getKey());
            }

        }


    }
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
        // put data from sorted list to hashmap

    }

}