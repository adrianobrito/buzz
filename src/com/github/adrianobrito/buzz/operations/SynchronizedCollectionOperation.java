package com.github.adrianobrito.buzz.operations;

import java.util.ArrayList;
import java.util.Collection;

public class SynchronizedCollectionOperation<T> implements CollectionOperations<T>{
	
	private Collection<T> collection;
	
	public SynchronizedCollectionOperation(Collection<T> collection) {
		this.collection = collection;
	}

	@Override
	public Collection<T> get() {
		return collection;
	}

	@Override
	public CollectionOperations<T> each(Function<? super T> f) {
		for(T t:collection){
			synchronized (t) {
				f.block(t);
			}
		}
		
		return this;
	}

	@Override
	public CollectionOperations<T> filter(Conditional<? super T> conditional) {
		
		Collection<T> collection = new ArrayList<T>();
		for(T t:this.collection){
			synchronized (t) {
				if(conditional.block(t))
					collection.add(t);
			}
		}
		
		this.collection = collection;
		return this;
	}
	
}
