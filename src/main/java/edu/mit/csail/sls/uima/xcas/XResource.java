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

import java.util.UUID;
import java.util.WeakHashMap;

import org.apache.uima.fit.component.Resource_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ExternalResourceLocator;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.resource.ExternalResourceDescription;

/**
 * XResource is an eXternal Resource for an arbitrary object
 */
public class XResource<T> implements ExternalResourceDescriptionProvider<T> {
	final String uuid;
	final ExternalResourceDescription externalResourceDescription;

	static final WeakHashMap<String, Object> resources = new WeakHashMap<>();
	public static class Trampoline extends Resource_ImplBase implements ExternalResourceLocator {

		final static String PARAM_UUID = "uuid";
		
		@ConfigurationParameter(name = PARAM_UUID)
		String uuid;

		@Override
		public Object getResource() {
			synchronized (resources) {
				return resources.get(uuid);
			}
		}
	}

	/**
	 * Make resource available as an external resource.
	 * @param resource
	 */
	public XResource(T resource){
		String guuid;
		synchronized(resources){
			while(true){
				guuid = UUID.randomUUID().toString();
				if (resources.containsKey(guuid))
					continue;
				resources.put(guuid, resource);
				break;
			}
		}
		uuid = guuid;
		externalResourceDescription = ExternalResourceFactory.createExternalResourceDescription(Trampoline.class,
				Trampoline.PARAM_UUID, uuid);
	}
	
	/**
	 * Make resource available as an external resource.
	 */
	public XResource(){
		this(null);
	}

	protected void setResource(T resource){
		resources.put(uuid, resource);
	}
	
	@SuppressWarnings("unchecked")
	public T getResource(){
		return (T)resources.get(uuid);
	}
	
	/**
	 * Release storage associated with this resource
	 */
	public void release(){
		synchronized(resources){
			resources.remove(uuid);
		}
	}
	
	protected void finalize(){
		release();
	}

	public ExternalResourceDescription getResourceDescription() {
		return externalResourceDescription;
	}
}