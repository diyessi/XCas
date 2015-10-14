package edu.mit.csail.sls.uima.xcas;

public interface XCasFactoryResource<T> extends ExternalResourceDescriptionProvider {
	XCasFactory<T> getXCasFactory();
}
