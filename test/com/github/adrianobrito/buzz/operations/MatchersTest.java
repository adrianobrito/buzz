package com.github.adrianobrito.buzz.operations;

import static com.github.adrianobrito.buzz.Buzz.collection;
import static com.github.adrianobrito.buzz.Buzz.syncCollection;
import static com.github.adrianobrito.buzz.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchersTest {

	public class Data{
		private Integer id;
		private Byte data;
		
		public Data(Integer id, Byte data) {
			super();
			this.id = id;
			this.data = data;
		}
		
		public Integer getId() { return id; }
		public void setId(Integer id) { this.id = id; }
		
		public Byte getData() { return data; }
		public void setData(Byte data) { this.data = data; }
		
	}
	
	private final static Integer MAX_LENGTH = 100;
	private final List<Integer> numbers = new ArrayList<Integer>();
	private List<String> strings;
	private List<Data> dataList;
	
	@Before
	public void before(){
		dataList = Arrays.asList(new Data(1, (byte)5), new Data(2, (byte)5), new Data(3, (byte)8));
		strings = Arrays.asList("Adriano", "Clara", "Tito", "Isauro");
		for(int i=0; i < MAX_LENGTH; i++)
			numbers.add(i);
	}
	
	@Test
	public void shouldBeGreaterThan(){
		List<Integer> subList = (List<Integer>)collection(numbers).filter(greaterThan(50)).get();
		
		for(Integer i:subList)
			assertThat(i > 50, is(true));
	}
	
	@Test
	public void shouldBeGreaterThanEquals(){
		List<Integer> subList = (List<Integer>)collection(numbers).filter(greaterEqualsThan(50)).get();
		
		for(Integer i:subList)
			assertThat(i >= 50, is(true));
	}
	
	@Test
	public void shouldBeLessThan(){
		List<Integer> subList = (List<Integer>)collection(numbers).filter(lessThan(50)).get();
		
		for(Integer i:subList)
			assertThat(i < 50, is(true));
	}
	
	@Test
	public void shouldBeLessThanEquals(){
		List<Integer> subList = (List<Integer>)collection(numbers).filter(lessEqualsThan(50)).get();
		
		for(Integer i:subList)
			assertThat(i <= 50, is(true));
	}
	
	@Test
	public void shouldBeEquals(){
		List<Integer> subList = (List<Integer>)collection(numbers).filter(equalsTo(50)).get();
		
		for(Integer i:subList)
			assertThat(i == 50, is(true));
	}
	
	@Test
	public void shouldBeNotEquals(){
		List<Integer> subList = (List<Integer>)collection(numbers).filter(notEqualsTo(50)).get();
		
		for(Integer i:subList)
			assertThat(i != 50, is(true));
	}
	
	@Test
	public void shouldContainString(){
		List<String> subList = (List<String>)collection(strings).filter(containsString("Adriano")).get();
		
		for(String i:subList)
			assertThat(i, is("Adriano"));
	}
	
	@Test
	public void shouldMatchObject(){
		Data data = new Data(2, null);
		List<Data> subList = (List<Data>)syncCollection(dataList).filter(matches(data)).get();
		
		for(Data i:subList)
			assertThat(i.getId(), is(2));
	}
	
	@Test
	public void shouldPropertyMatchObject(){
		List<Data> subList = (List<Data>)syncCollection(dataList).filter(propertyMatches("id", 2)).get();
		
		for(Data i:subList)
			assertThat(i.getId(), is(2));
	}
	
	@Test
	public void shouldPropertyMatchObjectWithConditional(){
		List<Data> subList = (List<Data>)syncCollection(dataList).filter(propertyMatches("id", greaterThan(2))).get();
		
		for(Data i:subList)
			assertThat(i.getId() > 2, is(true));
	}
	
	
}
