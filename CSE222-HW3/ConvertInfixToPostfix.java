import java.io.*;
/**
 * Translates infix expression to postfix expression.
 */
public class ConvertInfixToPostfix {
    /*Constant string to keep operators sin(,cos( and abs(.*/
    public static final String KEEPSOME = ("sin(abs(cos(");
    /**
     * This class keeps two string variable.I used this class while reading test_file_part2 file.
     */
    private static class KeepVariables {
        //Data fields.
        private String variable;
        private String value;
        /*This constructor initialize data fields*/
        private KeepVariables(String variable, String value) {
            this.variable = variable;
            this.value = value;
        }
        /**Gets string.
         * @return string.
         */
        private String getVariable() {
            return variable;
        }
        /**
         * Gets string.
         * @return string.
         */
        private String getValue() {
            return value;
        }
        /**toString method.
         *Prints strings on screen.
         * @return strings.
         */
        public String toString() {
            return ("Variable: " + variable + " Value: " + value);

        }
    }

    /**
     * This class reads file.
     * @param fileRead the file.
     * @return the expressions in file.
     */
    public static String readFile(String fileRead) throws IOException,FileNotFoundException {
        File file=new File(fileRead);
        FileInputStream read = new FileInputStream(file);
        DataInputStream read2 = new DataInputStream(read);
        String str = read2.readLine();
        String str2 = str;
        KeepVariables[] arr = new KeepVariables[30];
        int i = 0;
        //Reads variables in file.
        while (str2 != null) {
            if (str.charAt(2) == '=') {
                String var = str.substring(0, 1);
                String value = str.substring(4, str.length());
                arr[i] = new KeepVariables(var, value);
                i++;
            }
            str = read2.readLine();
            str2 = str;
            if (str.isEmpty()) {
                str2 = read2.readLine();
                str = str2;
                for (int z = 0; z < i; z++) {
                    str = str.replaceAll(arr[z].getVariable(), arr[z].getValue());
                }
                str2 = read2.readLine();
            }
        }
        read2.close();
        read.close();
        return str;
    }
    /**
     * Converts a infix expression to postfix expression.
     * @param strInfix The infix expression that will translate a postfix expression.
     * @return Postfix expression of given  expression.
     */
    public static String  convert(String strInfix) {
        /*Stack of characters.*/
        MyStack<Character> stackOp = new MyStack<Character>();
        /*Adds substrings with each other.*/
        StringBuilder join = new StringBuilder();
        /*String array  to all substrings except blank.*/
        String[] elements=strInfix.split("\\s+");
        //This loop creates postfix expression using variable of stringbuilder and stack.
        for(int i=0;i < elements.length;i++){
                if(elements[i].equals("sin(") || elements[i].equals("cos(") || elements[i].equals("abs(")){
                    String which=elements[i];
                    StringBuilder build = new StringBuilder();
                    MyStack<String> stack = new MyStack<>();
                    stack.push("(");
                    build.append("("+" ");
                    while(!stack.isEmpty()){
                        ++i;
                        if(elements[i].equals("(") || KEEPSOME.contains(elements[i])) {
                            stack.push("(");
                        }
                        else if(elements[i].equals(")")) {
                            stack.pop();
                        }
                        build.append(elements[i]+" ");

                    }
                    join.append(convert(build.toString()));
                    if(which.equals("sin("))
                        join.append("sin ");
                    else if(which.equals("cos("))
                        join.append("cos ");
                    else{
                        join.append("abs ");
                    }
                }
                else{
                    Character symbol = elements[i].charAt(0);
                    if ((Character.isLetter(symbol) || Character.isDigit(symbol)&&elements[i].length()==1)) {
                        join.append(symbol+" ");
                    }
                    else if(elements[i].length()!=1){
                        for(int j = 0;j<elements[i].length();j++){
                            join.append(elements[i].charAt(j));
                        }
                        join.append(" ");
                    }
                    else if (isOperator(symbol) == true) {
                        if (stackOp.isEmpty() == true) {
                            stackOp.push(symbol);
                        }
                        else if (symbol == '(') {
                            stackOp.push(symbol);
                        }
                        else if (symbol == ')') {
                            while (stackOp.isEmpty() == false && stackOp.peek() != '(') {
                                join.append(stackOp.peek()+" ");
                                stackOp.pop();
                            }
                            stackOp.pop();
                        }
                        else if (precedence(symbol) > precedence(stackOp.peek())) {
                            stackOp.push(symbol);
                        }
                        else {
                            int stop = 0;
                            while (stackOp.isEmpty() == false && precedence(symbol) <= precedence(stackOp.peek()) && stop == 0) {
                                if (stackOp.peek() == '(') {
                                    stop = 1;
                                }
                                join.append(stackOp.peek() + " ");
                                stackOp.pop();
                            }
                            if (symbol != ')') {
                                stackOp.push(symbol);
                            }
                        }
                    }
                }
        }
        //If there are any elements of stack,add them to postfix expresion.
        while(stackOp.isEmpty()==false){
            join.append(stackOp.pop()+" ");
        }
        return join.toString();
    }

    /**
     * Determines whether a character is operand or not.
     * @param c The given character.
     * @return Result of whether it is operand..
     */
    public static boolean isOperator(char c){
        if(c =='+' || c =='-' || c== '*' ||c=='/' || c =='(' || c==')' ||c=='^')
            return true;
        else
            return false;
    }

    /**
     * Finds precedence of an  operator.
     * @param c Given operator.
     * @return Precedence of an operator.
     */
    public static int precedence(char c){
        if(c=='(' || c==')')
            return -1;
        else if(c=='+' || c=='-')
            return 1;
        else if(c=='*' || c=='/')
            return 2;
        else if(c== '^')
            return 3;
        else return -1;
    }
}
