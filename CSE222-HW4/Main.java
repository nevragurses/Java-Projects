/**
 * This is a driver class to test Traverse2DArray class.
 */
public class Main {
        public static void main(String[] args) {
            int[][] arr=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,16}};
            int column=0,count=0;
            System.out.println("THE GIVEN 2D ARRAY:");
            //Prints the 2D array on screen.
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    count ++;
                    column=count;
                    if(arr[i][j]<10)
                        System.out.print(arr[i][j] + "   ");
                    else
                        System.out.print(arr[i][j] + "  ");
                }
                count=0;
                System.out.printf("\n");
            }
            Traverse2DArray tr = new Traverse2DArray(arr,column,arr.length);
            System.out.println("\nTHE ITERATOR ORDER IS:");
            tr.TraverseWithIterator();

        }
}



