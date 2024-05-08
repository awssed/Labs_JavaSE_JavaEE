package Task2_CarParking.Car;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private int id;
    private Semaphore firstSemaphore;
    private Semaphore secondSemaphore;

    public Car(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    private boolean goToFirst()
    {
        try {
        if(firstSemaphore.tryAcquire())
        {
            System.out.println("Car "+this.id+" has taken first parking lot");
            Thread.sleep(2000);
            firstSemaphore.release();
            return true;
        }
        else
        {
            System.out.println("Car "+this.id+" is going to second parking lot");
            Thread.sleep(2000);
            return false;
        }
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    }
    private boolean goToSecond()
    {
        try {
            if (secondSemaphore.tryAcquire()) {
                System.out.println("Car " + this.id + " has taken second parking lot");
                Thread.sleep(2000);
                secondSemaphore.release();
                return true;
            } else {
                System.out.println("Car " + this.id + " is going to first parking lot");
                Thread.sleep(2000);
                return false;
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void run()
    {
        boolean foundParking=false;
        while (!foundParking)
        {
            foundParking=goToFirst();
            if(foundParking)
                break;
            foundParking=goToSecond();
        }
    }

    public void setFirstSemaphore(Semaphore firstSemaphore) {
        this.firstSemaphore = firstSemaphore;
    }

    public void setSecondSemaphore(Semaphore secondSemaphore) {
        this.secondSemaphore = secondSemaphore;
    }

    public Semaphore getFirstSemaphore() {
        return firstSemaphore;
    }

    public Semaphore getSecondSemaphore() {
        return secondSemaphore;
    }
}
