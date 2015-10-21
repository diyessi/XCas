package edu.mit.csail.sls.uima.xcas;

/**
 * Method for manipulating configuration data
 */
class ConfigurationData {
	/**
	 * 
	 * @param originalArgs
	 * @param moreArgs
	 * @return moreArgs prepended to originalArgs
	 */
	static Object[] prepend(Object[] originalArgs, Object ... moreArgs){
		Object[] newArgs = new Object[originalArgs.length + moreArgs.length];
		int pos = 0;
		for(Object o : moreArgs){
			newArgs[pos++] = o;
		}
		for(Object o : originalArgs){
			newArgs[pos++] = o;
		}
		return newArgs;
	}
	
	/**
	 * 
	 * @param originalArgs
	 * @param moreArgs
	 * @return originalArgs extended by moreArgs
	 */
	static Object[] extend(Object[] originalArgs, Object ... moreArgs){
		Object[] newArgs = new Object[originalArgs.length + moreArgs.length];
		int pos = 0;
		for(Object o : originalArgs){
			newArgs[pos++] = o;
		}
		for(Object o : moreArgs){
			newArgs[pos++] = o;
		}
		return newArgs;
	}

}
