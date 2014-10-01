package com.github.adrianobrito.buzz;

import java.util.Collection;

import com.github.adrianobrito.buzz.operations.AsynchronousCollectionOperation;
import com.github.adrianobrito.buzz.operations.BasicCollectionOperations;
import com.github.adrianobrito.buzz.operations.CollectionOperations;


public class Buzz {
	
	public static <T> CollectionOperations<T> collection(Collection<T> collection){
		return new BasicCollectionOperations<T>(collection);
	}
	
	public static <T> CollectionOperations<T> asyncCollection(Collection<T> collection){
		return new AsynchronousCollectionOperation<T>(collection);
	}
	
	public static <T> CollectionOperations<T> syncCollection(Collection<T> collection){
		return new BasicCollectionOperations<T>(collection);
	}
	
}
