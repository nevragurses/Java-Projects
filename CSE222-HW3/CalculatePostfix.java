/**
 * Calculates result of a postfix expression.
 */
public class CalculatePostfix {
    //Constant number Pi.
    public static final double PI =3.14159;
    /**
     * Calculates result of a given postfix expression with digits and operators.
     * @param convertedInfix The postfix expression.
     * @return Result of mathematical calculations.
     */
    public static double calculate(String convertedInfix) {
        //Data fields.
        /*Stack of numbers.*/
        MyStack<Double> keep = new MyStack<>();
        int z=0;
        /*String array to all substrings except blank.*/
        String[] strArr = convertedInfix.split("\\s");
        int i = 0;
        /*Result of calculation.*/
        double result=0;
        //This loop finds result of calculations with using stack.
        while (i< strArr.length) {
            Character chr = strArr[i].charAt(0);
            if (strArr[i].equals("sin") || strArr[i].equals("cos") || strArr[i].equals("abs")) {
                result=calculateOperations(strArr[i], keep);
                keep.push(result);
            }
            else if(strArr[i].length() !=1 && chr=='-'){
                result=Double.parseDouble(strArr[i]);
                keep.push(result);
            }
            else if(isItOperator(chr)){
                String s=chr.toString();
                result=calculateOperations(s,keep);
                keep.push(result);
            }
            else{
                result=Double.parseDouble(strArr[i]);
                keep.push(result);
            }
            i++;
        }
        double sum=keep.pop();
        return sum;
    }

    /**
     * Determines whether a character is operand or not.
     * @param c The given character.
     * @return Result of whether it is operand.
     */
    public static boolean isItOperator(char c){
        if(c =='+' || c =='-' || c== '*' ||c=='/' || c =='(' || c==')' ||c=='^')
            return true;
        else
            return false;
    }

    /**
     * Calculates factorial of a number.
     * @param n The given number.
     * @return Result of calculation of factorial.
     */
    public static int calculateFactorial(int n){
        int i,fact=1;
        for(i=1;i<=n;++i)
        {
            fact=fact*i;
        }
        return fact;
    }

    /**
     * Power calculation of a number.
     * @param x The given number.
     * @param n Power of it.
     * @return result of power calculation.
     */
    public static double calculatePower(double x,int n){
        int count=1;
        double multiplication=1;
        if(x!=0 && n==0){
            return 1;
        }
        else if(x!=0 || n!=0)
        {
            while(count<=n)
            {
                multiplication=multiplication*x;
                ++count;
            }
        }
        return multiplication;

    }
    /**
     * Calculates sinüs of a degree with using Taylor Teorem.
     * @param x The given degree.
     * @return result of sinüs calculation.
     */
    public static double calculateSin(double x){
            double radian;
            radian=x*(PI/180);
            double result=0;
            int i,term;
            for(i=1;i<=6;i++)
            {
                result+=calculatePower(-1,i-1)*(calculatePower(radian,2*i-1)/calculateFactorial(2*i-1));
            }
            return result;
    }

    /**Evaluates the operation.
     * This function pops the two operands from the stack then calculates operation.
     * If operator is abs or sin or cos,pop one operand from the stack and evaluate operation.
     * @param s Operator string.
     * @param stack The stack of numbers and operators.
     * @return result of calculation.
     */
    public static double calculateOperations(String s, MyStack<Double> stack){
        double sum=0;
        double right,left;
        if(s.equals("+")){
            right =stack.pop();
            left=stack.pop();
            sum=right+left;
            return sum;
        }
        else if(s.equals("*")){
            right =stack.pop();
            left=stack.pop();
            sum=right*left;
            return sum;
        }
        else if(s.equals("/")){
            double temp=stack.pop();
            double temp2=stack.pop();
            right=temp2;
            left=temp;
            sum=right/left;
            return sum;
        }
        else if(s.equals("-")){
            double temp=stack.pop();
            double temp2=stack.pop();
            right=temp2;
            left=temp;
            sum=right-left;
            return sum;
        }
        else if(s.equals("sin")){
            double sin=calculateSin(stack.pop());
            return sin;
        }
        else if(s.equals("cos")){
            double cos=90-stack.pop();
            return calculateSin(cos);
        }
        else if(s.equals("abs")){
            double abs=stack.pop();
            if(abs < 0){
                abs=-1*abs;
                return abs;
            }
            else {
                return abs;
            }
        }
        else if(s.equals("^")){
            double temp=stack.pop();
            double temp2=stack.pop();
            right=temp2;
            left=temp;
            System.out.println(calculatePower(right,(int)left));
            return calculatePower(right,(int)left);
        }
        else
            return 0;
    }
}
