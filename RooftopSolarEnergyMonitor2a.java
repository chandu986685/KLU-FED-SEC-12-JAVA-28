public class RooftopSolarEnergyMonitor2a {
    public static void main(String[] args) {
        
        int panelId = 101;
        double energyGenerated = 12.5; 
        int numberOfPanels = 8;
        char systemStatus = 'A'; 
       
        System.out.println("--- Rooftop Solar System Details ---");
        System.out.println("Panel ID: " + panelId);
        System.out.println("Energy Generated: " + energyGenerated + " kWh");
        System.out.println("Number of Solar Panels: " + numberOfPanels);
        System.out.println("System Status: " + systemStatus);
    }
    
}
