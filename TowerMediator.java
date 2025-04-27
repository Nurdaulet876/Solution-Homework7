public interface TowerMediator {
    void broadcast(String msg, Aircraft sender);
    boolean requestRunway(Aircraft aircraft);
}
