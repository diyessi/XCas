package edu.mit.csail.sls.uima.xcas;

import org.apache.uima.resource.ExternalResourceDescription;

public interface ExternalResourceDescriptionProvider<T> {
	/**
	 * 
	 * @return an ExternalResourceDescription for this object
	 */
	ExternalResourceDescription getResourceDescription();
}
