/*
 * Copyright (c) 2015, Massachusetts Institute of Technology
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package edu.mit.csail.sls.uima.xcas;

import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

abstract public class XCasCollectionReader_ImplBase<XCas> extends JCasCollectionReader_ImplBase {
	
	@ExternalResource(key = XCasResource.PARAM_XCAS_RESOURCE)
	protected XCasResource<XCas> xCasResource;
	
	public static <XCas> CollectionReaderDescription createReaderDescription(Class<? extends CollectionReader> cls, XCasResource<XCas> resource, Object ... configurationData) 
			throws ResourceInitializationException {
		return CollectionReaderFactory.createReaderDescription(cls,
				ConfigurationData.prepend(configurationData, XCasResource.PARAM_XCAS_RESOURCE, resource.getResourceDescription()));
	}
	
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);
		initialize(context, createXCas());
	}
	
	public void initialize(UimaContext context, XCas xCas) throws ResourceInitializationException{
	}
	
	protected XCas createXCas(){
		return xCasResource.createXCas();
	}

	abstract public void getNext(JCas jcas, XCas xCas) throws org.apache.uima.collection.CollectionException;
	
	@Override
	public void getNext(JCas jCas) throws CollectionException {
		getNext(jCas, xCasResource.setNewXCas(jCas));
	}

}
