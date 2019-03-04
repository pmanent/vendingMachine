/**
 * 
 */
package com.simple.vending.command;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.vending.exception.ValidationException;
import com.simple.vending.rule.Rule;

/**
 * Class that implements all the generic methods that all subclases of command can execute.
 *  
 * @author Pere Manent
 *
 */
public abstract class CommandImpl implements Command {

	private final Logger logger = LoggerFactory.getLogger(CommandImpl.class);

	/**
	 * Rules that validates the actual command can be executed. If the list is
	 * empty, does not really matters. The method has to executed normally.
	 */
	private List<Rule> rules = new ArrayList<Rule>();

	/**
	 * Method used to add as many rules needed to validate the process.
	 * 
	 * @param rule
	 */
	public void addRule(Rule rule) {
		logger.debug("addRule rule " + rule);
		rules.add(rule);
	}

	/**
	 * Method that validates all the rules.
	 * 
	 * @throws SimpleBankingException
	 */
	public void validate() throws ValidationException {
		logger.debug("Starting the Command Validation Process ");
		rules.stream().forEach((rule) -> {
			logger.debug("Validating rule " + rule);
			rule.validate();
		});
	}

}
