package com.github.adrianobrito.buzz.operations;

import java.util.Collection;

public interface CollectionOperations<T> {
	
	Collection<T> get();
	CollectionOperations<T> each(Function<T> f);
	CollectionOperations<T> filter(Conditional<?> conditional);
	
	public interface Function<T>{
		void block(T t);
	}
	
	public interface Conditional<T>{
		boolean block(T t);
	}
	
}
