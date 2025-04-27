public class PassengerPlane extends Aircraft{
    public PassengerPlane(String id, int fuelLevel, TowerMediator tower){
        super(id, fuelLevel, tower);
    }

    @Override
    public void receive(String msg){
        System.out.println("[PassengerPlane " + id + "] received: " + msg);
    }
}
