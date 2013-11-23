package person;

import agent.StringUtil;
import application.gui.trace.AlertLog;
import application.gui.trace.AlertTag;

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
    	//System.out.println(roleName + " " + getName() + " : " + msg);
    	if (roleName.equals("Bank Customer") || roleName.equals("Bank Guard") 
    			|| roleName.equals("Bank Teller") || roleName.equals("Loan Officer"))
    	{
            AlertLog.getInstance().logInfo(AlertTag.BANK, roleName + " " + getName(), msg);

    	}
    	else if (roleName.equals("Maintenance Worker"))
    	{
            AlertLog.getInstance().logInfo(AlertTag.HOUSING, "Mouse", msg);

    	}
    	else if (roleName.equals("Market Customer") || roleName.equals("Market Runner") 
    			|| roleName.equals("Sales Person") || roleName.equals("UPS Man"))
    	{
            AlertLog.getInstance().logInfo(AlertTag.MARKET, roleName + " " + getName(), msg);

    	}
    	else if (roleName.equals("Alternative Waiter") || roleName.equals("Cashier")
    			|| roleName.equals("Cook") || roleName.equals("Host") 
    			|| roleName.equals("Restaurant Customer") || roleName.equals("Normal Waiter"))
    	{
            AlertLog.getInstance().logInfo(AlertTag.RESTAURANT, roleName + " " + getName(), msg);

    	}
    	else
    	{
            AlertLog.getInstance().logInfo(AlertTag.PERSON, roleName + " " + getName(), msg);
    		
    	}
    		
    }

    /**
     * Print message with exception stack trace
     */
    protected void print(String msg, Throwable e) {
        StringBuffer sb = new StringBuffer();
        sb.append("role ");
        sb.append(roleName);
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
