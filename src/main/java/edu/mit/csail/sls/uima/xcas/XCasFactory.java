package edu.mit.csail.sls.uima.xcas;

public interface XCasFactory<T> {

	/**
	 * Create a new XCas instance
	 * @return the new XCas
	 */
	T createXCas();

}
