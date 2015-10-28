package edu.mit.csail.sls.uima.xcas;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * Initialize the document language and text 
 */
public class XCasDocumentInitializer extends XCasAnnotator_ImplBase<XCasDocumentInitializer.XCas> {
	public static interface XCas {
		String getDocumentLanguage();
		String getDocumentText();
	}
	
	public static <T extends XCas> AnalysisEngineDescription createEngineDescription(XCasResource<T> resource) throws ResourceInitializationException {
		return createEngineDescription(XCasDocumentInitializer.class, resource);
	}

	@Override
	public void process(JCas jCas, XCas xCas) throws AnalysisEngineProcessException {
		jCas.setDocumentLanguage(xCas.getDocumentLanguage());
		jCas.setDocumentText(xCas.getDocumentText());
	}

}
