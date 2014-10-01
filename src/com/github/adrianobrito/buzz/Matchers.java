package com.github.adrianobrito.buzz;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

import com.github.adrianobrito.buzz.operations.CollectionOperations.Conditional;

public class Matchers {
		
	public static Conditional<? super Number> greaterThan(final Number number){
		return new Conditional<Number>() {
			@Override
			public boolean block(Number t) {
				return new BigDecimal(t.toString()).compareTo(new BigDecimal(number.toString())) == 1;
			}
		};
	}
	
	public static Conditional<? super Number> lessThan(final Number number){
		return new Conditional<Number>() {
			@Override
			public boolean block(Number t) {
				return new BigDecimal(t.toString()).compareTo(new BigDecimal(number.toString())) == -1;
			}
		};
	}
	
	public static Conditional<? super Number> lessEqualsThan(final Number number){
		return new Conditional<Number>() {
			@Override
			public boolean block(Number t) {
				return new BigDecimal(t.toString()).compareTo(new BigDecimal(number.toString())) == -1 || 
					   new BigDecimal(t.toString()).compareTo(new BigDecimal(number.toString())) == 0;
			}
		};
	}
	
	public static Conditional<? super Number> greaterEqualsThan(final Number number){
		return new Conditional<Number>() {
			@Override
			public boolean block(Number t) {
				return new BigDecimal(t.toString()).compareTo(new BigDecimal(number.toString())) == 1 || 
					   new BigDecimal(t.toString()).compareTo(new BigDecimal(number.toString())) == 0;
			}
		};
	}
	
	public static <T> Conditional<? super T> equalsTo(final T t){
		return new Conditional<T>(){
			@Override
			public boolean block(T x) {
				return x.equals(t);
			}
		};
	}
	
	public static <T> Conditional<? super T> notEqualsTo(final T t){
		return new Conditional<T>(){
			@Override
			public boolean block(T x) {
				return !x.equals(t);
			}
		};
	}
	
	public static <T> Conditional<? super String> containsString(final String t){
		return new Conditional<String>(){			
			@Override
			public boolean block(String x) {
				return x.contains(t);
			}			
		};
	}
	
	public static <T> Conditional<? super Date> before(final Date t){
		return new Conditional<Date>(){			
			@Override
			public boolean block(Date x) {
				return x.before(t);
			}			
		};
	}
	
	public static <T> Conditional<? super Date> after(final Date t){
		return new Conditional<Date>(){			
			@Override
			public boolean block(Date x) {
				return x.after(t);
			}			
		};
	}
	
	public static <T> Conditional<? super T> matches(final Object t){
		return new Conditional<T>(){
			@Override
			public boolean block(T x) {
				boolean match = true;
				
				Field[] fields = t.getClass().getDeclaredFields();
				for(Field f:fields){
					f.setAccessible(true);
					try{ 
						Object valueT = f.get(t);
						Object valueX = f.get(x);
						
						if(valueT != null || valueX != null)
							if(valueT != null)
								match = match && valueT.equals(valueX);
							else 
								match = match && valueX.equals(valueT);
					} catch(Exception e){ }					
				}
				
				return match;
			}
		};
	}
	
	public static <T> Conditional<T> propertyMatches(final String property, final Object value){
		return new Conditional<T>(){

			@Override
			public boolean block(T t) {
				try {
					Field field = t.getClass().getDeclaredField(property);
					field.setAccessible(true);
					return field.get(t).equals(value);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			
		};
	}
	
	public static <T> Conditional<T> propertyMatches(final String property, final Conditional<? super Object> conditional){
		return new Conditional<T>(){

			@Override
			public boolean block(T t) {
				try {
					Field field = t.getClass().getDeclaredField(property);
					field.setAccessible(true);
					return conditional.block(field.get(t));
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			
		};
	}
	
}
