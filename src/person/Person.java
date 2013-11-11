package person;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import agent.Agent;
import restaurant.Waiter;

/**
 * Base class for a Person
 */
public class Person extends Agent {

	protected boolean pickAndExecuteAnAction() {
		return false;
	}

}

