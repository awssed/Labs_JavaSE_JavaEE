package Task1_CallCenter.Client;

import java.util.concurrent.Semaphore;

public class Client {
    private int id;
    private Semaphore semaphore;

    public Client(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
