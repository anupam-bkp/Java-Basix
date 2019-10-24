package futuretasktest;

import java.util.concurrent.Callable;

/**
 * @author ashraf
 *
 */
public class CallableCalculater implements Callable<Long> {

	private long first;
	private long last;
	private long divisor;
	

	/**
	 * Instantiates a new callable calculater.
	 *
	 * @param first the first
	 * @param last the last
	 * @param divisor the divisor
	 */
	public CallableCalculater(long first, long last, long divisor) {
		this.first = first;
		this.last = last;
		this.divisor = divisor;
	}


	@Override
	public Long call() throws Exception {

		return Calculater.calculateNumberOfDivisible(first, last, divisor);
	}

}
