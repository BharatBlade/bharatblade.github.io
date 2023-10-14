package College;
public class LockClient {

    public static void main (String [] args) {
        CombinationLock cl = new CombinationLock();
        System.out.println(cl);
        cl.turn(false, 4);
        cl.turn(true, 8);
    }
}
