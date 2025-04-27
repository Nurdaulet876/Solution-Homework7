public abstract class Aircraft{
    protected String id;
    protected int fuelLevel;
    protected TowerMediator tower;

    public Aircraft(String id, int fuelLevel, TowerMediator tower) {
        this.id = id;
        this.fuelLevel = fuelLevel;
        this.tower = tower;
    }

    public void send(String msg) {
        tower.broadcast(msg, this);
    }

    public abstract void receive(String msg);

    public String getId() {
        return id;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}

