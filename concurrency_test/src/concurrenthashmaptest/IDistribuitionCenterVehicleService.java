package concurrenthashmaptest;

public interface IDistribuitionCenterVehicleService<T> {
	public void arrivalQueue(T vehicle);
	public boolean unloadInProgress(T vehicle);
	public void departureQueue(T vehicle);
}
