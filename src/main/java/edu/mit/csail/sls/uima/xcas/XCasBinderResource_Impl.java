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

import java.util.WeakHashMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.fit.component.Resource_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResourceLocator;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ExternalResourceDescription;

/**
 * A weak association between CAS and an external CAS.
 * garbage collection does not count the map as a reference to the CAS, and,
 * if the CAS is freed, the map does not count as a reference to the value.
 *
 * @param <T> The type of the value
 */
abstract public class XCasBinderResource_Impl<T> extends Resource_ImplBase implements XCasFactoryBinderResource<T>, XCasFactoryBinder<T>, XCasFactory<T>, ExternalResourceLocator {
	
	protected WeakHashMap<CAS, T> index = new WeakHashMap<>();
	
	@Override
	public XCasBinder<T> getXCasBinder(){
		return this;
	}
	
	@Override
	public XCasFactory<T> getXCasFactory(){
		return this;
	}
		
	@Override
	public synchronized T setXCas(CAS cas, T value){
		index.put(cas, value);
		return value;
	}
	
	@Override
	public synchronized T getXCas(CAS cas){
		return index.get(cas);
	}

	@Override
	public T removeXCas(CAS cas){
		return index.remove(cas);
	}

	@Override
	public T setXCas(JCas jcas, T value){
		return setXCas(jcas.getCas(), value);
	}
	
	@Override
	public T getXCas(JCas jcas){
		return getXCas(jcas.getCas());
	}

	@Override
	public T removeXCas(JCas jcas){
		return removeXCas(jcas.getCas());
	}
	
	@Override
	public T createXCas(){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public T setNewXCas(CAS cas){
		return setXCas(cas, createXCas());
	}

	public T setNewXCas(JCas jCas){
		return setXCas(jCas, createXCas());
	}

	@Override
	public ExternalResourceDescription getResourceDescription(){
		return ExternalResourceFactory.createExternalResourceDescription(this.getClass());
	}
}
