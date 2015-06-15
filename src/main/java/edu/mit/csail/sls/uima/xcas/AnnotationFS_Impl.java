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
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

public class AnnotationFS_Impl implements AnnotationFS {
	CAS cas;
	int begin;
	int end;
	
	protected AnnotationFS_Impl(CAS cas, int begin, int end){
		this.cas = cas;
		this.begin = begin;
		this.end = end;
	}

	protected AnnotationFS_Impl(JCas jcas, int begin, int end){
		this(jcas.getCas(), begin, end);
	}

	@Override
	public CAS getView() {
		return cas;
	}

	@Override
	public Type getType() {
		return null;
	}

	@Override
	public void setFeatureValue(Feature feat, FeatureStructure fs)
			throws CASRuntimeException {
	}

	@Override
	public FeatureStructure getFeatureValue(Feature feat)
			throws CASRuntimeException {
		return null;
	}

	@Override
	public void setStringValue(Feature feat, String s)
			throws CASRuntimeException {
	}

	@Override
	public String getStringValue(Feature f) throws CASRuntimeException {
		return null;
	}

	@Override
	public float getFloatValue(Feature feat) throws CASRuntimeException {
		return 0;
	}

	@Override
	public void setFloatValue(Feature feat, float f) throws CASRuntimeException {
	}

	@Override
	public int getIntValue(Feature feat) throws CASRuntimeException {
		return 0;
	}

	@Override
	public void setIntValue(Feature feat, int i) throws CASRuntimeException {
	}

	@Override
	public byte getByteValue(Feature feat) throws CASRuntimeException {
		return 0;
	}

	@Override
	public void setByteValue(Feature feat, byte i) throws CASRuntimeException {
	}

	@Override
	public boolean getBooleanValue(Feature feat) throws CASRuntimeException {
		return false;
	}

	@Override
	public void setBooleanValue(Feature feat, boolean i){
	}

	@Override
	public short getShortValue(Feature feat) throws CASRuntimeException {
		return 0;
	}

	@Override
	public void setShortValue(Feature feat, short i) throws CASRuntimeException {
	}

	@Override
	public long getLongValue(Feature feat) throws CASRuntimeException {
		return 0;
	}

	@Override
	public void setLongValue(Feature feat, long i) throws CASRuntimeException {
	}

	@Override
	public double getDoubleValue(Feature feat) throws CASRuntimeException {
		return 0;
	}

	@Override
	public void setDoubleValue(Feature feat, double i)
			throws CASRuntimeException {
	}

	@Override
	public String getFeatureValueAsString(Feature feat)
			throws CASRuntimeException {
		return null;
	}

	@Override
	public void setFeatureValueFromString(Feature feat, String s)
			throws CASRuntimeException {
	}

	@Override
	public CAS getCAS() {
		return cas;
	}

	@Override
	public int getBegin() {
		// TODO Auto-generated method stub
		return begin;
	}

	@Override
	public int getEnd() {
		// TODO Auto-generated method stub
		return end;
	}

	@Override
	public String getCoveredText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object clone(){
		return null;
	}


}
