import java.util.*;
import java.util.concurrent.*;

public class MediatorPatternDemo{
    private static Random random = new Random();
    private static List<Aircraft> aircrafts = new ArrayList<>();
    private static ControlTower tower = new ControlTower();

    public static void main(String[] args) {
        spawnAircrafts(10);
        startSimulation();
    }

    private static void spawnAircrafts(int count) {
        for (int i = 0; i < count; i++) {
            String id = "AC" + (i + 1);
            int fuel = random.nextInt(50) + 5;
            Aircraft aircraft = switch (random.nextInt(3)) {
                case 0 -> new PassengerPlane(id, fuel, tower);
                case 1 -> new CargoPlane(id, fuel, tower);
                default -> new Helicopter(id, fuel, tower);
            };
            tower.registerAircraft(aircraft);
            aircrafts.add(aircraft);
        }
    }

    private static void startSimulation() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            Aircraft aircraft = getRandomAircraft();

            if (aircraft.getFuelLevel() < 8) {
                aircraft.send("MAYDAY");
            } else if (tower.requestRunway(aircraft)) {
                handleRunwayUsage(aircraft);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private static Aircraft getRandomAircraft() {
        return aircrafts.get(random.nextInt(aircrafts.size()));
    }

    private static void handleRunwayUsage(Aircraft aircraft) {
        System.out.println(aircraft + " is using the runway...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        tower.releaseRunway();
    }
}
