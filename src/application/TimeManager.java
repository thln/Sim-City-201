package application;

public class TimeManager {

	private static final long startTime = System.currentTimeMillis();

	private static TimeManager timeManager;

	public enum Day{Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday};

	private TimeManager() {
	}

	public static TimeManager getTimeManager() {
		if (timeManager == null) {
			timeManager = new TimeManager();
			return timeManager;
		}
		else {
			return timeManager;
		}
	}

	public Time getTime() {
		//Could implement a "time slider"
		long runTime;
		runTime = System.currentTimeMillis() - startTime;

		long simulationMinutes;
		simulationMinutes = runTime/500;
		Time time = new Time((int) simulationMinutes);

		return time;
	}

	public class Time {
		public final int dayMinute;
		public final int dayHour;
		public Day day = Day.Sunday;

		//Used to calculate simulation time
		Time(int simulationMinutesSinceBegnningOfTime) {
			dayMinute = (int) simulationMinutesSinceBegnningOfTime % 60;
			dayHour = ((int) simulationMinutesSinceBegnningOfTime / 60) % 24;

			int daysSinceStartOfTime;
			daysSinceStartOfTime = (simulationMinutesSinceBegnningOfTime / 60) / 24;

			int dayOfWeek;
			dayOfWeek = daysSinceStartOfTime % 7;

			switch (dayOfWeek) {
			case 0:
				day = Day.Sunday;
				break;
			case 1:
				day = Day.Monday;
				break;
			case 2:
				day = Day.Tuesday;
				break;
			case 3:
				day = Day.Wednesday;
				break;
			case 4:
				day = Day.Thursday;
				break;
			case 5:
				day = Day.Friday;
				break;
			case 6:
				day = Day.Saturday;
				break;
			}
		}
	}
}
