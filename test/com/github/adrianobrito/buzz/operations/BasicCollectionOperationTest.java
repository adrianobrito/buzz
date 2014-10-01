package com.github.adrianobrito.buzz.operations;

import static com.github.adrianobrito.buzz.Buzz.collection;
import static com.github.adrianobrito.buzz.Matchers.greaterThan;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.adrianobrito.buzz.operations.CollectionOperations.Function;

public class BasicCollectionOperationTest {
	
	private final static Integer MAX_LENGTH = 100;
	private final List<Integer> numbers = new ArrayList<Integer>();
	private Function<Integer> printFunction = new Function<Integer>() {
		@Override
		public void block(Integer t) {
			System.out.println(t);
		}
	};
	
	@Before
	public void before(){
		for(int i=0; i < MAX_LENGTH; i++)
			numbers.add(i);
	}
	
	@Test
	public void shouldIterate(){
		collection(numbers).each(printFunction);
	}
	
	@Test
	public void shouldFilter(){
		List<Integer> subList = (List<Integer>)collection(numbers).filter(greaterThan(50)).get();
	
		for(Integer i:subList)
			assertThat(i > 50, is(true));
	}
	
}
