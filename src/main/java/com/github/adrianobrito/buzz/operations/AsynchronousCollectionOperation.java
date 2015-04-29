package com.github.adrianobrito.buzz.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AsynchronousCollectionOperation<T> implements CollectionOperations<T> {
	
private Collection<T> collection;
	
	private final ExecutorService processingPool = Executors.newFixedThreadPool(20);

	public AsynchronousCollectionOperation(Collection<T> collection) {
		this.collection = collection;
	}

	@Override
	public Collection<T> get() {
		return collection;
	}

	@Override
	public CollectionOperations<T> each(final Function<? super T> f) {
		for(final T t:collection){
			processingPool.execute(new Runnable() {
				
				@Override
				public void run() {
					f.block(t);
					
				}
			});
		}
		
		return this;
	}

	@Override
	public CollectionOperations<T> filter(final Conditional<? super T> conditional) {
		
		final Collection<T> collection = new ArrayList<T>();
		final AtomicInteger counter = new AtomicInteger();
		
		for(final T t:this.collection){
			processingPool.execute(new Runnable() {
				
				@Override
				public void run() {
					if(conditional.block(t))
						collection.add(t);
					
					counter.incrementAndGet();
				}
			});
		}
		
		//Waiting
		while(counter.get() < this.collection.size()){}
		this.collection = collection;
		
		return this;
	}

    @Override
    public CollectionOperations<T> where(String bql) {
        return null;
    }
}
