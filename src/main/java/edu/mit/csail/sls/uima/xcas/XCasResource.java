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

import org.apache.uima.cas.CAS;
import org.apache.uima.fit.descriptor.ExternalResourceLocator;
import org.apache.uima.jcas.JCas;

/**
 * An external resource that associates values with a CAS.
 *
 * @param <T> The type of the value
 */
public interface XCasResource<T> extends ExternalResourceLocator, ExternalResourceDescriptionProvider {
	public static final String PARAM_XCAS_RESOURCE = "xCasResource";
	
	/**
	 * Associate a value with a CAS
	 * @param cas
	 * @param value
	 * @return the value
	 */
	T setXCas(CAS cas, T value);

	
	/**
	 * Gets the value associated with a CAS
	 * @param cas
	 * @return The associated value
	 */
	T getXCas(CAS cas);
	
	/**
	 * Remove a value associated with a CAS
	 * @param cas
	 * @return the previous value
	 */
	T removeXCas(CAS cas);

	/**
	 * Associate a value with a JCas
	 * @param jcas referencing a JCas
	 * @param value
	 * @return the value
	 */
	T setXCas(JCas jcas, T value);
	
	/**
	 * Gets the value associated with a JCas
	 * @param jcas referencing a JCas
	 * @return the associated value
	 */
	T getXCas(JCas jcas);
	
	/**
	 * Associate a value with a JCas
	 * @param jcas referencing a JCas
	 * @return the value
	 */
	T removeXCas(JCas jcas);

	/**
	 * Create a new XCas instance
	 * @return the new XCas
	 */
	T createXCas();
	
	/**
	 * Create a new XCas and associate it with the cas
	 * @param cas
	 * @return the new XCas
	 */
	T setNewXCas(CAS cas);
	
	/**
	 * Create a new XCas and associate it with the jCas
	 * @param jCas
	 * @return
	 */
	T setNewXCas(JCas jCas);
}
