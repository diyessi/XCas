package edu.mit.csail.sls.uima.xcas;

import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;

public interface XCasFactoryBinder<XCas> extends XCasFactory<XCas>, XCasBinder<XCas> {
	
	/**
	 * Create a new XCas and associate it with the cas
	 * @param cas
	 * @return the new XCas
	 */
	XCas setNewXCas(CAS cas);
	
	/**
	 * Create a new XCas and associate it with the jCas
	 * @param jCas
	 * @return
	 */
	XCas setNewXCas(JCas jCas);
}
