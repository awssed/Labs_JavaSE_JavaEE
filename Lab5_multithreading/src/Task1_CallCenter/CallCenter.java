package Task1_CallCenter;

import Task1_CallCenter.Client.Client;
import Task1_CallCenter.Operator.Operator;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class CallCenter implements Runnable {
    static ArrayList<Operator> operators;
    static Queue<Client> clients;
    static Semaphore semaphore;
    public CallCenter(ArrayList<Operator> listOperators,Queue<Client> listClients)
    {
        operators=listOperators;
        clients=listClients;
        semaphore=new Semaphore(operators.size());
    }
    private static Operator findAvalibleOperatore()
    {
        for(Operator operator:operators)
        {
            if(operator.isReady())
            {
                return operator;
            }
        }
        return null;
    }
    public void run()
    {

        while(!clients.isEmpty())
        {
            try{
                if(semaphore.tryAcquire()) {
                    Operator freeOperator = findAvalibleOperatore();
                    freeOperator.setCurrentClientId(clients.poll());
                    freeOperator.setSemaphore(semaphore);
                    freeOperator.setReady(false);
                    new Thread(freeOperator).start();
                }
                else{
                    Thread.sleep(2000);
                    System.out.println("Client"+clients.peek().getId()+"cant wait!");
                }
            }
            catch (Exception e)
            {
                System.out.println("Something wrong!");
            }
        }
    }

}
