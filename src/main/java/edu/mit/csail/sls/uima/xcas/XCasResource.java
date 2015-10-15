package edu.mit.csail.sls.uima.xcas;

import java.util.UUID;
import java.util.WeakHashMap;

import org.apache.uima.cas.CAS;
import org.apache.uima.fit.component.Resource_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ExternalResourceLocator;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ExternalResourceDescription;

abstract public class XCasResource<XCas> {
	public static final String PARAM_XCAS_RESOURCE = "xCasResource";
	
	// Holds each CAS->xCas resource
	static final WeakHashMap<String, XCasResource<?>> resources = new WeakHashMap<>();

	final String uuid;
	final protected WeakHashMap<CAS, XCas> index;;

	protected XCasResource() {
		this.index = new WeakHashMap<>();
		synchronized (resources) {
			uuid = UUID.randomUUID().toString();
			resources.put(uuid, this);
		}
	}

	protected void finalize() {
		synchronized (resources) {
			resources.remove(uuid);
		}
	}

	/**
	 * Create a new XCas instance
	 * 
	 * @return the new XCas
	 */
	protected abstract XCas createXCas();

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

	final static String PARAM_UUID = "uuid";

	static public class XCasBinderResourceDescription extends Resource_ImplBase implements ExternalResourceLocator {
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
	 * 
	 * @return A resource description
	 */
	public ExternalResourceDescription getResourceDescription() {
		return ExternalResourceFactory.createExternalResourceDescription(XCasBinderResourceDescription.class,
				PARAM_UUID, uuid);
	}
}
