package person;

public abstract class Role {

	Person person;
	
	enum roleState {active, inActive, waitingToExecute}
	roleState state = roleState.inActive;
	
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
    	state = roleState.inActive;
    	stateChanged();
    }
}
