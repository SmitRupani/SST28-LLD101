public class PublicityTool implements Event {
    private final EventPlanner planner;
    public PublicityTool(EventPlanner planner) { this.planner = planner; }

    @Override public void createEvent(String name, double budget) { planner.create(name, budget); }
    @Override public int getEventsCount() { return planner.count(); }
    
}
