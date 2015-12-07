package edu.mit.csail.sls.uima.xcas;

import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;

/**
 * UIMA assigns resources are engine initialization time, so if you want to reuse
 * an engine you need a resource that holds the actual resource so you can set it
 * outside of initialization.
 *
 * @param <T> The type of the mutable resource
 */
public class XMutableResource<T> extends XResource<Mutable<T>> implements Mutable<T> {
	public XMutableResource(T value){
		super(new MutableObject<>(value));
	}
	
	public XMutableResource(){
		this(null);
	}

	/**
	 * Get the value of mutable resource
	 */
	@Override
	public T getValue() {
		return getResource().getValue();
	}

	/**
	 * Change the value of the mutable resource
	 */
	@Override
	public void setValue(T value) {
		getResource().setValue(value);
	}
	
}
