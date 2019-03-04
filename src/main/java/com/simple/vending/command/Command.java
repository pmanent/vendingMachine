package com.simple.vending.command;

import com.simple.vending.exception.ValidationException;

/**
 * Main interface that declare the methods to execute a command.
 * 
 * The concrete classes have to implement this interface to implement a command.
 * 
 * @author Pere Manent.
 *
 */
public interface Command {
	/**
	 * Main method to execute a command.
	 * 
	 * @return
	 * @throws ValidationException
	 */
	public Object execute() throws ValidationException;
}
