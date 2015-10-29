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
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ExternalResourceDescription;

abstract public class XCasResource<XCas> implements ExternalResourceDescriptionProvider<XCasResource<XCas>> {
	public static final String PARAM_XCAS_RESOURCE = "xCasResource";
	public static final String PARAM_XCAS_RESOURCE_NEXT = "xCasResourceNext";
	

	final XResource<XCasResource<XCas>> xResource;
	final protected WeakHashMap<CAS, XCas> index;;

	protected XCasResource() {
		this.index = new WeakHashMap<>();
		xResource = new XResource<>(this);
	}

	protected void finalize() {
		xResource.release();
	}

	/**
	 * Create a new XCas instance
	 * 
	 * @return the new XCas
	 */
	public abstract XCas createXCas();

	/**
	 * Associate a value with a CAS
	 * @param cas
	 * @param xCas
	 * @return the xCas
	 */
	public synchronized XCas setXCas(CAS cas, XCas xCas) {
		index.put(cas, xCas);
		return xCas;
	}

	/**
	 * Gets the value associated with a CAS
	 * @param cas
	 * @return The associated xCas
	 */
	public synchronized XCas getXCas(CAS cas) {
		return index.get(cas);
	}

	/**
	 * Remove a value associated with a CAS
	 * @param cas
	 * @return the removed xCas
	 */
	public XCas removeXCas(CAS cas) {
		return index.remove(cas);
	}

	/**
	 * Associate a value with a JCas
	 * @param jcas referencing a JCas
	 * @param xCas
	 * @return the xCas
	 */
	public XCas setXCas(JCas jcas, XCas xCas) {
		return setXCas(jcas.getCas(), xCas);
	}

	/**
	 * Gets the value associated with a JCas
	 * @param jcas referencing a JCas
	 * @return the associated xCas
	 */
	public XCas getXCas(JCas jcas) {
		return getXCas(jcas.getCas());
	}

	/**
	 * Associate a value with a JCas
	 * @param jcas referencing a JCas
	 * @return the removed xCas
	 */
	public XCas removeXCas(JCas jcas) {
		return removeXCas(jcas.getCas());
	}

	/**
	 * Create a new XCas and associate it with the cas
	 * @param cas
	 * @return the new XCas
	 */
	public XCas setNewXCas(CAS cas) {
		return setXCas(cas, createXCas());
	}

	/**
	 * Create a new XCas and associate it with the jCas
	 * @param jCas
	 * @return the xCas
	 */
	public XCas setNewXCas(JCas jCas) {
		return setXCas(jCas, createXCas());
	}

	/**
	 * 
	 * @return A resource description
	 */
	@Override
	public ExternalResourceDescription getResourceDescription() {
		return xResource.getResourceDescription();
	}
}
