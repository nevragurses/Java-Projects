/**
 * My generic stack class.
 * @param <E> any data structure.
 */
public class MyStack<E> {
    /*ArrayList variable to do process.*/
    private MyArrayList<E> arrList;
    /*Size of stack.*/
    private int size;
    /**
     * No parameter constructor to initilize data fields.
     */
    MyStack() {
        arrList = new MyArrayList<E>();
        size = 0;
    }
    /**
     * Push an element in stack using add method of ArrayList.
     *
     * @param variable The given element.
     * @return Element that is added in stack.
     */
    public E push(E variable) {
        arrList.add(variable);
        ++size;
        return variable;
    }
    /**
     * Pop last element from the stack using remove method of ArrayList.
     *
     * @return element that is popped in stack.
     */
    public E pop() {
        E temp = arrList.get(arrList.getSize() - 1);
        arrList.remove(arrList.getSize() - 1);
        --size;
        return temp;
    }
    /**
     * Gets last element in stack using  get method of ArrayList.
     *
     * @return last element in stack.
     */
    public E peek() {
        E temp = arrList.get(arrList.getSize() - 1);
        return temp;
    }
    /**
     * Determines whether stack is empty or not.
     *
     * @return result of whether it is empty.
     */
    public boolean isEmpty() {
        return arrList.isEmpty();
    }
    /**
     * toString method.
     * Prints all elements in stack as string.
     *
     * @return string that is all elements in stack.
     */
    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        int i = 0;
        while (i < arrList.getSize()) {
            strBuild.append(arrList.get(i) + " ");
            i++;
        }
        return strBuild.toString();
    }
}
