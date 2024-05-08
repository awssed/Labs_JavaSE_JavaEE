package Task2_CarParking.CarPark;

import Task2_CarParking.Car.Car;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class CarPark implements Runnable {
   static private int _firstPark;
   static private int _secondPark;
   static private Semaphore firstSemaphore;
   static private Semaphore secondSemaphore;
   static ArrayList<Car> cars;


    public CarPark(int firstCount,int secondCount,ArrayList<Car> ListCars) {
        _firstPark=firstCount;
        _secondPark=secondCount;
        firstSemaphore=new Semaphore(_firstPark);
        secondSemaphore=new Semaphore(_secondPark);
        cars=ListCars;
        for(Car car:cars)
        {
            car.setFirstSemaphore(firstSemaphore);
            car.setSecondSemaphore(secondSemaphore);
        }
    }
    public void run() {
        for (Car car:cars)
        {
            new Thread(car).start();
        }
    }
}
