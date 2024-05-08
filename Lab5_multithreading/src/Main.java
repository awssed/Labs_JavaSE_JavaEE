import Task1_CallCenter.CallCenter;
import Task1_CallCenter.Client.Client;
import Task1_CallCenter.Operator.Operator;
import Task2_CarParking.Car.Car;
import Task2_CarParking.CarPark.CarPark;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        //----------TASK 1----------------
//        Queue<Client> client=new LinkedList<Client>();
//        client.add(new Client(1));
//        client.add(new Client(2));
//        client.add(new Client(3));
//        client.add(new Client(4));
//        client.add(new Client(5));
//        ArrayList<Operator> operators=new ArrayList<Operator>();
//        operators.add(new Operator(1));
//        operators.add(new Operator(2));
//        CallCenter center=new CallCenter(operators,client);
//        new Thread(center).start();
        //---------TASK2-----------------
        ArrayList<Car> cars=new ArrayList<Car>();
        cars.add(new Car(1));
        cars.add(new Car(2));
        cars.add(new Car(3));
        cars.add(new Car(4));
        cars.add(new Car(5));
        cars.add(new Car(6));
        cars.add(new Car(7));
        cars.add(new Car(8));
        cars.add(new Car(9));
        cars.add(new Car(10));
        cars.add(new Car(11));
        CarPark park=new CarPark(3,3,cars);
        new Thread(park).start();
    }
}