package Task1_CallCenter.Operator;

import Task1_CallCenter.Client.Client;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Operator  implements Runnable
{
    private int id;

    public void setCurrentClientId(Client currentClientId) {
        this.currentClientId = currentClientId.getId();
    }

    private int currentClientId;
    private boolean isReady;

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    private Semaphore semaphore;
    private final Random random = new Random();

    public Operator(int id) {
        this.id = id;
        this.isReady = true;
        semaphore=null;
    }

    public int getId() {
        return id;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
    public void run()
    {
        try {
            System.out.println("Operator " + id + " is serving client " + currentClientId);
            int serviceTime = random.nextInt(5000) + 1000;
            Thread.sleep(serviceTime);
            System.out.println("Operator " + id+ " has finished serving client " + currentClientId);
            this.isReady=true;
            semaphore.release();
        }
        catch (InterruptedException e)
        {
            System.out.println("Interuptted thread");
        }
    }

    public int getCurrentClientId() {
        return currentClientId;
    }
}
