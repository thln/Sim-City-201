package person;

import agent.StringUtil;

public abstract class Role {

	protected Person person;
	
	protected String roleName = "";
	protected String personName = "";
	
	enum RoleState {active, inActive, waitingToExecute}
	private RoleState state = RoleState.inActive;
	
	protected Role(Person person, String pName, String rName) {
		this.person = person;
		personName = pName;
		roleName = rName;
		state = RoleState.inActive;
	}
	
	protected void stateChanged() {
        //calls Person scheduler
		//do we just call the scheduler, or do we release the semaphore on stateChanged?
		person.pickAndExecuteAnAction();
    }

    protected abstract boolean pickAndExecuteAnAction();
 
	public RoleState getState() {
		return state;
	}

	public void setState(RoleState state) {
		this.state = state;
	}
	
	public String getName() {
		return person.getName();
	}
	
	  /**
     * Print message
     */
    protected void print(String msg) 
    {
    	System.out.println(roleName + " " + getName() + " : " + msg);
    }

    /**
     * Print message with exception stack trace
     */
    protected void print(String msg, Throwable e) {
        StringBuffer sb = new StringBuffer();
        sb.append(getName());
        sb.append(": ");
        sb.append(msg);
        sb.append("\n");
        if (e != null) {
            sb.append(StringUtil.stackTraceString(e));
        }
        System.out.print(sb.toString());
    }
    
    public String getRoleName () {
    	return roleName;
    }

}
