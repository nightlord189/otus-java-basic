package hw07.internal;

public interface ITransport extends Movable {
    void sit(Human driver);

    void getOff();
}
