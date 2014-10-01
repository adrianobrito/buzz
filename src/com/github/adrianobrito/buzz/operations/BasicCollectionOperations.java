package com.github.adrianobrito.buzz.operations;

import java.util.ArrayList;
import java.util.Collection;

public class BasicCollectionOperations<T> implements CollectionOperations<T>{
	
	private Collection<T> collection;
	
	public BasicCollectionOperations(Collection<T> collection) {
		this.collection = collection;
	}

	@Override
	public Collection<T> get() {
		return collection;
	}

	@Override
	public CollectionOperations<T> each(Function<T> f) {
		for(T t:collection){
			f.block(t);
		}
		
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public CollectionOperations<T> filter(Conditional conditional) {
		
		Collection<T> collection = new ArrayList<T>();
		for(T t:this.collection){
			if(conditional.block(t))
				collection.add(t);
		}
		
		this.collection = collection;
		return this;
	}
	

}
