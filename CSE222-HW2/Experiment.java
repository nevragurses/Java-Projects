import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
/**This is a Experiment class that stores features of experiments.
 *
 */
public class Experiment{
    //Data fields.
    /**Experimental setup of an experiment.*/
    private String setup;
    /**The start day of experiment*/
    private int day;
    /**The start time of experiment.*/
    private String time;
    /**Indicates Whether experiment completed or not.*/
    private boolean completed;
    /**Accurancy of experiment.*/
    public float accuracy;
    /**Constructor that initilizes features of an experiment.
     * If completed is false,accuracy be invalid.
     * @param setup Experimental setup of an experiment.
     * @param day The start day of experiment.
     * @param time The start time of experiment.
     * @param completed Whether an experiment completed or not.
     * @param accuracy Accuracy of an experiment.
     */
    public Experiment(String setup,String time,boolean completed,int day,float accuracy) {
        this.setup=setup;
        this.day=day;
        this.time=time;
        this.completed=completed;
        if(this.completed==false){
            this.accuracy= -1;
        }
        else if(this.completed==true) {
            this.accuracy = accuracy;
        }
    }
    /**No parameter constructor of Experiment class*/
    public Experiment(){
        this.day=1;
        this.completed=true;
        String time1=new SimpleDateFormat("1:10:10").format(new Date());
        this.time=time1;
        this.accuracy=100;

    }

    /**Returns experimental setup of an experiment.*/
    public String getSetup() { return setup; }
    /**Returns start day of experiment.*/
    public int getDay() { return day; }
    /**Returns start time of an experiment.*/
    public String getTime() { return time; }
    /**Returns completed state of an experiment.*/
    public boolean getCompleted() { return completed; }
    /**Returns accuracy of an experiment.*/
    public float getAccuracy() { return accuracy; }
    /**Prints features of an experiment.*/
    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", accuracy=" + accuracy +
                ", completed=" + completed +
                '}';
    }
}