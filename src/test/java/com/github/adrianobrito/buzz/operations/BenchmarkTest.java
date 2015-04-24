package com.github.adrianobrito.buzz.operations;

import static com.github.adrianobrito.buzz.Buzz.asyncCollection;
import static com.github.adrianobrito.buzz.Buzz.collection;
import static com.github.adrianobrito.buzz.Buzz.syncCollection;
import static com.github.adrianobrito.buzz.Matchers.greaterThan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.adrianobrito.buzz.operations.CollectionOperations.Conditional;
import com.github.adrianobrito.buzz.operations.CollectionOperations.Function;

public class BenchmarkTest {
	
	public static void main(String[] args) {
		
		final List<Integer> numbers = new ArrayList<Integer>();
		for(int i=0; i < 100; i++)
			numbers.add(i);
		
		Long asyncTime = test(new Runnable() {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void run() {
				asyncCollection(numbers).each(new Function(){ public void block(Object i){ stop(10); }});				
			}
			
		});
		
		Long basicTime = test(new Runnable() {
			
			@Override
			public void run() {
				collection(numbers).each(new Function<Integer>(){ public void block(Integer i){ stop(10); }});				
			}
			
		});
		
		Long syncTime = test(new Runnable() {
			
			@Override
			public void run() {
				syncCollection(numbers).each(new Function<Integer>(){ public void block(Integer i){ stop(10); }});				
			}
			
		});
		
		System.out.println(
				String.format("Basic: %d\n"
							+ "Sync: %d\n"
							+ "Async: %d\n", basicTime, syncTime, asyncTime));
		
		
		Collection<Integer> filteredList = collection(numbers).filter(new Conditional<Integer>(){public boolean block(Integer t) { return t < 500; }})
								.get();	
		
		
		System.out.println(collection(numbers).filter(greaterThan(50)).get());
		System.out.println("Tamanho da nova lista: " + filteredList.size());
		
	}
	
	public static long test(Runnable runnable){
		long init = System.currentTimeMillis();
		runnable.run();
		long end = System.currentTimeMillis();
		
		return end - init;
	}
	
	public static void stop(long time){
		try{
			Thread.sleep(time);
		} catch(Exception e){}
	}

}
