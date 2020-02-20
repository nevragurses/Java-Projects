import java.util.NoSuchElementException;
import java.util.Iterator;
/** Iterable ExperimentList class to store experiments of each day.*/
public class ExperimentList implements Iterable<Experiment>{
    //data fields.
    /**Reference to the head of ExperimentList.*/
    private Node<Experiment>head=null;
    /**The number of all experiments.*/
    private int size=0;
    /**The number of experiments that is first experiment of each day.*/
    private int size2=0;

    /**This is a inner class  to create  Node of ExperimentList.
     *
     * @param <Experiment> Node elements is a Experiment class type.
     */
    private static class Node <Experiment> {
        //Data fields.
        /**The Reference to data.*/
        private Experiment data;
        /**Reference to the next experiment node.*/
        private Node<Experiment> next;
        /**Reference to the first experiment node of days.*/
        private Node<Experiment> day;

        /**Constructor that creates new node with null next experiment.
         *
         * @param data Data that is stored.
         */
        private Node(Experiment data){
            this.data = data;
            next = null;
            day=null;
        }
        /**Constructor that creates new node that referances next experiment node and next day node.
         *
         * @param next The node referenced by new created node to keep experiments.
         * @param day  The node referenced by new created node to keep first experiment of days .
         * @param experiment The data that is stored.
         */
        private Node(Node<Experiment> next,Node<Experiment>day,Experiment experiment) {
            data = experiment;
            this.next = next;
            this.day=day;
        }
    }
    /**
     * This is a my Iterator class to use iterator() function of ExperimentList class.
     */
    public class ExpListIterator implements Iterator<Experiment>{
        //Data fiels.
        /**Reference to current node.*/
        Node<Experiment> forward;
        /**Reference for last returned node.*/
        Node <Experiment> last;
        /**Reference for the another last returned node.*/
        Node<Experiment> otherLast;

        /**Constructor that initilizes references.*/
        public ExpListIterator(){
            forward=head;
            last=null;
            otherLast=null;
        }

        /**
         * Determine whether next method returns a value.
         * @return  true if  next method returns a value.
         */
        public boolean hasNext(){
            return forward!=null;
        }
        /** Gets the next experiment.If there are no more experiments throws NoSuchElementException.
         *
         * @return the next experiment.
         */
        public Experiment next(){
            if(forward==null)
                throw new NoSuchElementException();
            otherLast=last;
            last=forward;
            forward=forward.next;
            return last.data;
        }

        /** Removes the last experiment that is returned by next method.
         * If last returned experiment is null,throws IllegalStateException.
         */
        public void remove(){
            if(last==null)
                throw new IllegalStateException();
            else if(otherLast==null){
                head=forward;
                otherLast=null;
                last=null;
            }
            else{
                otherLast.next=forward;
                last=otherLast;
            }
            size--;
        }
    }

    /**This is a helper function to addExp function.It adds first experiment node to ExperimentList.
     *
     * @param ex The experiment that is added.
     */
    private void addStart(Experiment ex){
        Node<Experiment> temp= new Node<Experiment>(head,head,ex);
        head=temp;
        ++size;
        ++size2;
    }

    /**This is a helper function to addExp function.It adds experiment after certain  node of ExperimentList.
     *
     * @param node Node that new experiment is added after this node.
     * @param node2 Node that references new day if it is occured.
     * @param ex The experiment that is added.
     */
    private void addAfter(Node<Experiment> node,Node<Experiment> node2,Experiment ex){
        if(node.data.getDay()!=ex.getDay() ){
            Node<Experiment>other=new Node<Experiment>(node.next,node2.day,ex);
            node.next=other;
            node2.day=other;
            ++size;
            ++size2;
        }
        else{
            Node<Experiment> temp= new Node<Experiment>(node.next,null,ex);
            node.next=temp;
            ++size;
        }
    }

    /**Insert given experiment to the end of the day.
     * If experiment is first experiment of a day,links the first experiment of the previous one day and it.
     *
     * @param experiment The experiment that is inserted.
     */
    public void addExp(Experiment experiment){
        if(size==0 )
            addStart(experiment);

        else{
            Node <Experiment> node=head;
            int i=0,flag=0;
            while( node!=null && flag==0 ){
                if(node.next==null)
                    flag=1;
                else{
                    while(node.next!=null && (node.next.data.getDay()<=experiment.getDay())){
                        node=node.next;
                        ++i;
                        flag=1;
                    }
                }
            }
            addAfter(node,getNode2(i-1),experiment);
        }
    }
    /**This is a helper function to setExp and getExp function.
     * It gets  experiment node that is certain day and index.
     *
     * @param day The day of experiment.
     * @param index The position of experiment.
     * @return Node that is certain day and position.
     */
    private Node<Experiment> getNodeWithIndex(int day,int index){
        Node< Experiment> node=head;
        int i=0,keep=0;
        while((node!=null && keep==0 )){
            if(node.data.getDay()==day && i<index){
                node=node.next;
                i++;
            }
            else if( node.data.getDay()==day && i>=index){
                keep=1;
            }
            else{
                node=node.next;
            }
        }
        return node;
    }

    /**Sets the experiment with the given day and position.
     *
     * @param day The day of experiment.
     * @param index The position of experiment.
     * @param e The experiment that is set instead of experiment that with given day and position.
     */
    public void setExp(int day,int index,Experiment e ){
        Node<Experiment> node= getNodeWithIndex(day,index);
        node.data=e;
    }

    /**Gets the experiment with the given day and position.
     *
     * @param day The day of experiment.
     * @param index The position of experiment.
     * @return Experiment that is given day and position.
     */
    public Experiment getExp(int day,int index) {
        Node<Experiment> node= getNodeWithIndex(day,index);
        return node.data;
    }

    /**This is a helper function for removeExp function.
     * It removes first experiment of ExperimentList.
     *
     * @return experiment that is removed.
     */
    private Experiment removeFirst(){
        Node<Experiment> temp = head;
        if (head != null){
            if(head.day!=null && head.next.data.getDay()==head.data.getDay()){
                head.next.day=head.day;
            }
            head = head.next;
            --size;
            --size2;
            return temp.data;
        }
        else
            return null;
    }
    /**This is a helper function for removeExp function.
     * It remove the experiment after given experiment node.
     *
     * @return experiment that is removed.
     */
    private Experiment removeAfter(Node<Experiment> node1){
        Node<Experiment> keep=node1.next;
        if(keep!=null){
            node1.next=keep.next;
            --size;
            return keep.data;
        }
        else
            return null;
    }

    /** Remove the experiment specified as index from given day.
     *
     * @param day The day of experiment that will remove.
     * @param index The index of experiment that will remove.
     */
    public void removeExp(int day,int index){
        Node< Experiment> node=head;
        int i=0,out=0,j=0;
        if(index==0 && day==1){
            removeFirst();
        }
        else if(index==0 && day!=1){
            while(node.next !=null && node.next.data.getDay() < day){
                node=node.next;
                j++;
            }
            removeAfter(node);
        }
        else{
            while(node!=null && out==0){
                if(node.data.getDay()==day && i<index-1){
                    node=node.next;
                    i++;
                }
                else if( node.data.getDay()==day && i>=index-1){
                    out=1;
                }
                else
                    node=node.next;
                j++;

            }
            removeAfter(node);
        }
    }

    /**Removes all experiments in a given day.
     *
     * @param day The day of experiments that will remove.
     */
    public void removeDay(int day){
        Node<Experiment> node=head;
        int count=0;
        while(node!=null){
            if(node.data.getDay()==day){
                removeExp(day,count);
                node=node.next;
            }
            else{
                node=node.next;
            }
        }
    }
    /**List all completed experiments in a given day.
     *
     * @param day The given day on which  all completed experiments are listed.
     */
    public void listExp(int day){
        Node<Experiment>node=head;
        int keep=0;
        while(node!=null && keep==0){
            if(node.data.getDay()==day){
                while(node.next!=null&& node.data.getDay()==day){
                    if (node.data.getCompleted()==true){
                        System.out.println(node.data.toString());
                        node=node.next;
                    }
                    else if(node.data.getCompleted()==false){
                        node=node.next;
                    }
                }
                if((node.next==null && (node.data.getDay()==day)) && (node.data.getCompleted()==true)){
                    System.out.println(node.data.toString());
                }
                keep=1;
            }
            else
                node=node.next;
        }
    }

    /**Sorts the experiments in a given day according to the accuracy, the changes will be done on the list.
     *
     * @param day The given day on which experiments  is sorted.
     */
    public void orderDay(int day){
        Node<Experiment>node=head;
        Experiment tempNode=node.data;
        while(node!=null ){
            if(node.data.getDay()==day){
                Node<Experiment>another=node.next;
                while (another!=null && another.data.getDay()==day ){
                    if(node.data.getAccuracy()>another.data.getAccuracy()){
                        tempNode=node.data;
                        node.data=another.data;
                        another.data=tempNode;
                    }
                    another=another.next;
                }
                node=node.next;
            }
            else{
                node=node.next;
            }

        }
    }

    /** Sorts all the experiments in the list according to the accuracy.
     *  The original list should not be changed since the day list may be damage .
     *
     * @return the head of sorted list.
     */
    public Node<Experiment> orderExperiments(){
        ExperimentList tempList=new ExperimentList();
        Node<Experiment> node=head;
        while(node!=null){
            tempList.addExp(node.data);
            node=node.next;
        }
        node=tempList.head;
        Experiment tempNode=node.data;
        while(node!=null ){
            Node<Experiment>another=node.next;
            while (another!=null ){
                if(node.data.getAccuracy()>another.data.getAccuracy()){
                    tempNode=node.data;
                    node.data=another.data;
                    another.data=tempNode;
                }
                another=another.next;
            }
            node=node.next;
        }
        Node<Experiment>temp=tempList.head;
        while(temp!=null){
            System.out.println(temp.data.toString());
            temp=temp.next;
        }
        return tempList.head;
    }
    public void linkOfDays(){
        Node<Experiment> node=head;
        int i=0;
        while(node!=null){
            System.out.println("DAY REFERENCE:  "+node.data.toString());
            node=node.day;
            i++;
        }
    }

    /**Find the node that specified position.
     *It finds it by using next reference of each node.
     * @param index The position of node.
     * @return The node that is specified position.
     */
    private Node<Experiment> getNode(int index){
        Node <Experiment> node=head;
        int i=0;
        while(i<index && node!=null ){
            node=node.next;
            i++;
        }

        return node;
    }

    /**Find the node that is specified position.
     * It finds it by using day reference of each node.
     * @param index The position of node.
     * @return The node that is specified position.
     */
    private Node<Experiment> getNode2(int index){
        Node <Experiment> node=head;
        int i=0;
        while(i<index && node.day!=null){
            node=node.day;
            i++;
        }
        return node;
    }

    /**Gets size of ExperimentList.
     *
     * @return size of ExperimentList.
     */
    public int getSize(){
        return size;
    }

    /** Prınts on screen ExperimentList as list experiment view and list day view.
     *
     */
    public void listAll()
    {
        System.out.println("LIST EXPERIMENT VİEW:");
        Node last = head;
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.next;
        }
        System.out.println("\nLIST DAYS VİEW");
        last = head;
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.day;
        }
    }

    /**Creates ıterator over the ExperimentList.
     *
     * @return an Iterator over the ExperimentList.
     */
    @Override
    public Iterator<Experiment> iterator(){
        return new ExpListIterator();

    }
    /**Prints all features of each experiments.
     *
     * @return all features of each node.
     */
    public String toString(){
        Node<Experiment> node = head;
        StringBuilder result = new StringBuilder();
        while (node != null){
            result.append(node.data.toString());
            if (node.next != null)
                result.append("\n ");
            node = node.next;
        }
        return result.toString();
    }



}



