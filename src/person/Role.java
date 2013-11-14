package person;

public abstract class Role {

	protected Person person;
	
	enum roleState {active, inActive, waitingToExecute}
	private roleState state = roleState.inActive;
	
	protected Role() {
	}
	
	protected Role(Person person) {
		this.person = person;
	}
	
	protected void stateChanged() {
        //calls Person scheduler
		person.pickAndExecuteAnAction();
    }

    protected abstract boolean pickAndExecuteAnAction();
    
    protected void inactivateRole() {
    	setState(roleState.inActive);
    	stateChanged();
    }

	public roleState getState() {
		return state;
	}

	public void setState(roleState state) {
		this.state = state;
	}
}
