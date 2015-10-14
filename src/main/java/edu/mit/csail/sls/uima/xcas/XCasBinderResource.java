package edu.mit.csail.sls.uima.xcas;

public interface XCasBinderResource<T> extends ExternalResourceDescriptionProvider {
	XCasBinder<T> getXCasBinder();
}
