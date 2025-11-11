package hw07.internal;

public class Human extends Entity implements Movable {
    private int stamina;
    private final int staminaConsumption;
    private final String name;
    private ITransport transport;

    public int getStamina() {
        return stamina;
    }

    public Human(String name, int stamina, int staminaConsumption, ITransport transport) {
        this.stamina = stamina;
        this.staminaConsumption = staminaConsumption;
        this.name = name;
        this.transport = transport;
    }

    public void sitOnTransport(ITransport transport) {
        System.out.println("Sitting on transport " + transport);
        this.transport = transport;
        transport.sit(this);
    }

    public void getOffFromTransport() {
        System.out.println("Getting off from transport");
        this.transport.getOff();
        this.transport = null;
    }

    public void decreaseStamina(int staminaCost) {
        this.stamina -= staminaCost;
    }

    @Override
    public boolean move(Surface surface, int distance) {
        if (transport != null) {
            return transport.move(surface, distance);
        }

        int staminaCost = distance * staminaConsumption;
        if (stamina - staminaCost < 0) {
            System.out.println(getSimpleName() + " cannot move because is tired :(");
            return false;
        }
        stamina -= staminaCost;
        System.out.println(getSimpleName() + " moved to distance " + distance + " on surface " + surface + " on foot with stamina cost: " + staminaCost);
        return true;
    }
}
