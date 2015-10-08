package edu.mit.csail.sls.uima.xcas;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * Annotator that associates a new XCas with a jCas
 */
public class XCasCreateAnnotator extends JCasAnnotator_ImplBase {

	@ExternalResource(key = XCasResource.PARAM_XCAS_RESOURCE)
	protected XCasResource<Object> xCasResource;

	public static <T> AnalysisEngineDescription getDescription(XCasResource<T> resource)
			throws ResourceInitializationException {
		return AnalysisEngineFactory.createEngineDescription(XCasCreateAnnotator.class,
				XCasResource.PARAM_XCAS_RESOURCE, resource.getResourceDescription());
	}

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		xCasResource.setNewXCas(jCas);
	}

}
