import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.*;

/**The driver class of ExerimentList class.*/
public class Main {
    private static String list;

    public static void main(String[] args){
        String time1=new SimpleDateFormat("2:10:20").format(new Date());
        Experiment ex=new Experiment("EXP-A",time1,true,1,5.2f);
        String time2=new SimpleDateFormat("3:20:10").format(new Date());
        Experiment ex2=new Experiment("EXP-B",time2,true,1,2.1f);
        String time3=new  SimpleDateFormat("1:5:30").format(new Date());
        Experiment ex3=new Experiment("EXP-C",time3,true,2,50.0f);
        String time4=new  SimpleDateFormat("4:15:16").format(new Date());
        Experiment ex4=new Experiment("EXP-D",time4,true,3,25.4f);
        String time5=new  SimpleDateFormat("3:25.35").format(new Date());
        Experiment ex5=new Experiment("EXP-E",time5,true,3,20.6f);
        String time6=new  SimpleDateFormat("5:10:20").format(new Date());
        Experiment ex6=new Experiment("EXP-F",time6,true,2,12.4f);
        String time7=new  SimpleDateFormat("1:45:40").format(new Date());
        Experiment ex7=new Experiment("EXP-G",time7,false,1,99.8f);
        String time8=new  SimpleDateFormat("2:30:50").format(new Date());
        Experiment ex8=new Experiment("EXP-H",time8,true,4,38.5f);
        String time9=new  SimpleDateFormat("3:4:14").format(new Date());
        Experiment ex9=new Experiment("EXP-I",time9,true,3,29.3f);
        String time10=new  SimpleDateFormat("6:16:25").format(new Date());
        Experiment ex10=new Experiment("EXP-J",time10,true,4,55.7f);
        String time11=new  SimpleDateFormat("5:10:45").format(new Date());
        Experiment ex11=new Experiment("EXP-K",time11,true,5,100.0f);


        ExperimentList  list= new ExperimentList();
        list. addExp(ex);
        list.addExp(ex2);
        list.addExp(ex3);
        list.addExp(ex4);
        list.addExp(ex5);
        list.addExp(ex6);
        list.addExp(ex7);
        list.addExp(ex8);
        list.addExp(ex9);
        list.addExp(ex10);
        list.addExp(ex11);

        System.out.println("------------   EXPERIMENTS AFTER ADDED TRUE LOCATION-------------\n" );
        list.listAll();
        System.out.println("SİZE OF ALL EXPERIMENTS: "+list.getSize()+ "\n");
        System.out.println("\n\nTESTING REMOVEEXP METHOD: ");
        list.removeExp(1,0);
        System.out.println("AFTER REMOVE (day:1,index:0):\n" );
        list.listAll();
        System.out.println("SİZE THAT IS AFTER REMOVE:"+list.getSize());
        System.out.println("\n\nTESTING REMOVEDAY METHOD:");
        list.removeDay(1);
        System.out.println("AFTER REMOVE ALL EXPERIMENTS OF DAY:1\n" );
        list.listAll();
        System.out.println("SİZE THAT IS AFTER REMOVED ALL EXPERIMENTS OF DAY:1:"+list.getSize());
        System.out.println("\n\nTEST FOR  GETEXP METHOD:" );
        list.getExp(3,0);
        System.out.println("TOSTRING OF EXPERIMENT(day:3,index:0) USİNG GETEXP METHOD:\n" +list.getExp(3,0));
        System.out.println("\n\nTEST FOR SETEXP METHOD");
        String time12=new SimpleDateFormat("4:2:5").format(new Date());
        Experiment forSet=new Experiment("EXP-L",time12,true,2,49.5f);
        list.setExp(2,1,forSet);
        System.out.println("AFTER SET EXPERIMENT(day:2,index:1,and new experiment with accuracy 49.5) : \n");
        list.listAll();
        System.out.println("\n\nTEST FOR LİSTEXP METHOD:");
        System.out.println("AFTER LİST COMPLETED EXPERIMENTS OF DAY:2 ");
        list.listExp(2);
        System.out.println("\n\nTEST FOR  ORDERDAY METHOD:" );
        list.orderDay(2);
        System.out.println("EXPERIMENTLIST AFTER ORDER EXPERIMENTS OF (day:2):\n" );
        list.listAll();
        System.out.println("\n\nTEST FOR ORDEREXPERIMENTS METHOD(ORDERED EXPERIMENTS ONLY IN THIS METHOD):" );
        list.orderExperiments();
        System.out.println("\n\nAFTER ORDEREXPERIMENTS METHOD ACTUAL EXPERIMENTS DONT'T CHANGE:\n" );
        list.listAll();


        System.out.println("\nTEST FOR  ITERATOR METHOD:" );
        System.out.println("TESTING HASNEXT AND NEXT METHOD OF MY ITERATOR CLASS:" );
        Iterator<Experiment> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
            iter.remove();
        }
        System.out.println("\nAFTER ALL ELEMENTS REMOVED IN EXPERIMENTLİST BY REMOVE METHOD OF  MY ITERATOR CLASS:" );

        System.out.println("SİZE OF EXPERIMENTLİST:"+list.getSize());

    }
}
