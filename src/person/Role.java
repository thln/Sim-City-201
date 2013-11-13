package person;

public class Role {

	enum roleState {active, inActive, waitingToExecute}
	roleState state = roleState.inActive;
}
