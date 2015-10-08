# Summary
`XCas` is a simple design pattern that augments the Apache UIMA `CAS` to
provide modular type-safe access to external resources. Every potential user
of an `XCas` defines the interface that it requires. A pipeline defines one or
more `XCas` resources that provide access to implementations of the required
interfaces. At appropriate points during processing, a `CAS` is bound to such
an implementation. Annotators include the resource specification and obtain
the `XCas` implementation from their `CAS` during processing.

# `XCas` Annotator Definition
An `XCas` annotator defines a public static `XCas` interface that declares all the methods
used by the annotator.  The class `XCasAnnotator_ImplBase<XCas>` provides a convenient mechanism 
for declaring the resource parameter and making the `XCas` resource available to the annotator.
For example, the following defines an annotator that requires one `XCas` method:

```
public class SignalWordsAnnotator
extends XCasAnnotator_ImplBase<SignalWordsAnnotator.XCas> 
{
   public static interface XCas
   {
      SignalWords getSignalWords();
   }
	
   public static <T extends XCas> 
   AnalysisEngineDescription getDescription(XCasResource<T> resource)
   throws ResourceInitializationException 
   {
      return getDescription(SignalWordsAnnotator.class, resource);
   }

   @Override
   public void process(JCas jCas, XCas xCas) 
   throws AnalysisEngineProcessException 
   {
        xCas.getSignalWords().compute(jCas);
   }
}
```
The `getDescription` method returns an analysis engine description for this annotator, given a resource
that implements the `XCas` interface. The `XCasAnnotator_ImplBase` implements the `process(JCas jCas)`
method, obtains the `XCas` associated with the `XCas`, and passes both to process.
# Resource Definition
A resource must pair an implementations of one or more `XCas` interfaces with each `JCas`.  One way to
do this is to use an `XCasCreateAnnotator` with an init arg of the resource, and put the annotator
 in the pipeline ahead of the `XCas` annotators.  Readers can also perform the association.
```
public class QuestionASResource 
implements
ChunkVectorAnnotator.XCas,
ClosestChunkAnnotator.QuestionXCas,
ClosestSentenceAnnotator.QuestionXCas,
CommentInitializer.QuestionXCas,
Configuration.QuestionXCas,
Doc2VecAnnotator.XCas,
Doc2VecFeatureVectorAnnotator.QuestionXCas,
DocumentInitializer.XCas,
DocumentVectorAnnotator.XCas,
QuestionCommentCASMultiplier.XCas,
SentenceVectorAnnotator.XCas,
TokenVectorAnnotator.XCas,
TreeStringAnnotator.XCas,
WordWeightAnnotator.XCas
{
   // Define a resource
   public static class Resource extends XCasResource_Impl<FeatureCollector> {
      @Override
      public Object getResource() {
         return resource;
      };
      
      @Override
      public FeatureCollector createXCas(){
         return new FeatureCollector();
      }
   }
   public static final Resource resource = new Resource();

   // Implement the XCas interfaces
   @Override
   SignalWords getSignalWords()
   {
      ...
   }
}
```
The resource is a singleton that associates a `JCas` with a `QuestionASResource`.  The class
`XCasResource_Impl<XCas>` extends  `ExternalResourceLocator` to provide methods for managing the associations,
as well as a `getResourceDescription` method which creates a `ExternalResourceDescription` for this resource.
# Setting up a Pipeline
# Binding an `XCas` to a `JCas`
# `XCas` Strategies
# Implementation
