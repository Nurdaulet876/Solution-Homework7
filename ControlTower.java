import java.util.*;

public class ControlTower implements TowerMediator{
    private Queue<Aircraft> landingQueue = new LinkedList<>();
    private Queue<Aircraft> takeOffQueue = new LinkedList<>();
    private boolean runwayAvailable = true;
    private List<Aircraft> allAircrafts = new ArrayList<>();

    public void registerAircraft(Aircraft aircraft){
        allAircrafts.add(aircraft);
    }

    @Override
    public void broadcast(String msg, Aircraft sender){
        if("MAYDAY".equalsIgnoreCase(msg)){
            System.out.println("\n[ControlTower] EMERGENCY! " + sender + " requesting immediate landing!");
            handleEmergency(sender);
        }else{
            System.out.println("[ControlTower] Broadcast from " + sender + ": " + msg);
            for(Aircraft aircraft: allAircrafts){
                if(aircraft!=sender){
                    aircraft.receive(sender + " says: " + msg);
                }
            }
        }
    }

    @Override
    public synchronized boolean requestRunway(Aircraft aircraft){
        if(aircraft.getFuelLevel() < 10){
            System.out.println("[ControlTower] LOW FUEL! Giving priority to " + aircraft);
            landingQueue.add(aircraft);
            return false;
        }

        if(runwayAvailable){
            runwayAvailable = false;
            System.out.println("[ControlTower] Runway cleared for " + aircraft);
            return true;
        }else{
            System.out.println("[ControlTower] Runway busy. Adding " + aircraft + " to queue.");
            landingQueue.add(aircraft);
            return false;
        }
    }

    public synchronized void releaseRunway(){
        runwayAvailable = true;
        System.out.println("[ControlTower] Runway is now available.");

        if(!landingQueue.isEmpty()){
            Aircraft next = landingQueue.poll();
            System.out.println("[ControlTower] Next for landing: " + next);
            next.send("Cleared for landing");
            requestRunway(next);
        }else if(!takeOffQueue.isEmpty()){
            Aircraft next = takeOffQueue.poll();
            System.out.println("[ControlTower] Next for takeoff: " + next);
            next.send("Cleared for takeoff");
            requestRunway(next);
        }
    }

    private void handleEmergency(Aircraft emergencyAircraft){
        landingQueue.clear();
        takeOffQueue.clear();
        runwayAvailable = true;
        for(Aircraft aircraft: allAircrafts){
            if(aircraft != emergencyAircraft){
                aircraft.receive("Hold position! Emergency landing in progress.");
            }
        }
        System.out.println("[ControlTower] Emergency aircraft " + emergencyAircraft + " cleared immediately!");
        requestRunway(emergencyAircraft);
    }
}
