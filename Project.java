import java.lang.*;
import java.util.*;
public class Project
{  HashMap<String,String> h = new HashMap<String,String>();      // to store key value pairs
   HashMap<String,Long> ht = new HashMap<String,Long>();        // to store time limit for corresponding keys
   long ttl = 0,time=0;int timelimit;
   public static void main(String[] args)
   {
   int optn; char optn2;
   String value = new String(); 
   String key = new String(); 
   Project obj = new Project();
   Scanner scan = new Scanner(System.in);
   System.out.println(" Please select the options to perform desired actions");
   do
   {
   System.out.println("1. create   2. read    3. delete");
   optn = scan.nextInt();
   if(optn == 1)
  {
   System.out.println(" Enter Key ");
   key = scan.next();
   System.out.println(" Enter value ");
   value = scan.next();
   System.out.println(" Enter timelimit ");
   obj.timelimit = scan.nextInt();
   obj.create(key,value);
   }
  else if(optn == 2)
  { 
   System.out.println(" Enter key to read ");
   key = scan.next();
   obj.read(key);}
   else 
   { 
   System.out.println(" Enter key to delete ");
   key = scan.next();obj.delete(key);
   }
   System.out.println(" continue (y/n) ? ");
   optn2 = scan.next().charAt(0);}
   while(optn2 == 'y');
   }
   public void create(String k,String v)
   { 
   if(h.containsKey(k))
      System.out.println(" This key already exists");
   else
   { 
   if( h.size() < 1000*1000*1000 && v.length() < 16*1000)           //constraints for file size less than 1GB & JSON object value less than 16KB 
   {
   if(ttl == 0)
         { ttl = System.currentTimeMillis();  ht.put(k,(long)0); }
    else
         { time = System.currentTimeMillis();  ht.put(k,time-ttl); }        // to calculate time taken for processing the keys
   if(k.length() <= 32)                                                                      //constraints for key length <= 32
      h.put(k,v);
   else
     System.out.println(" !! key length > 32 ! not stored in database !!");
   }
   else
       System.out.println("!! memory limit exceeded !!");
  }}
  public void read(String k)  
  { 
  if(! h.containsKey(k))
      System.out.println(" This key doesn't exists");
  else
   {
     if((ht.get(k))/60000 > timelimit)                           // converting milliseconds to minutes and check if it exceeds timelimit
       System.out.println(" time to live for " +k+ " has expired");
     else if(time == 0)
      { 
      if((System.currentTimeMillis()-ttl)/60000 > timelimit)  System.out.println(" time to live for " +k+ " has expired");
         else System.out.println(h.get(k));
      }
     else if((System.currentTimeMillis() - time)/60000 > timelimit )
         System.out.println("time to live for " +k+ " has expired ");
     else
           System.out.println(h.get(k));
  }}
   public void delete(String k)
   {
   if(! h.containsKey(k))
      System.out.println(" This key doesn't exists in database");
   else
    { 
     if((ht.get(k))/60000 > timelimit)
          System.out.println(" time to live for " +k+ " has expired");
   else if(time == 0)
      { 
      if((System.currentTimeMillis()-ttl)/60000 > timelimit)  System.out.println(" time to live for " +k+ " has expired");
         else System.out.println(h.get(k));
      }
   else if((System.currentTimeMillis() - time)/60000 > timelimit )
         System.out.println(" time to live for " +k+ " has expired");
   else
         { h.remove(k);
        System.out.println("key is deleted successfully"); }
   }
   }}