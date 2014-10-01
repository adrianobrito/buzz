# Buzz DSL

Buzz is a DSL for that who doesn't can use Java 8 new features for Collections, but wants to have some expressive way to handle Java Collections. The DSL provide intuitive elements for select and query data from any collection. Let's see the main features.

First of all, you need to put two required imports on your Java File to use Buzz DSL.
```java
import static com.github.adrianobrito.buzz.Buzz.*;
import static com.github.adrianobrito.buzz.Matchers.*;
```

##Collections Handlers

You have 3 ways of handle a collection on Buzz:

* Basic Collection: Handles the collection as a basic collection that runs on a Single Thread. Use the operation `collection()` to work with your collection as a basic collection.
* Synchronized Collection: Handles the collection as a Thread-Safe collection, and locks an element when it is acessed.  Use the operation `syncCollection()` to work with your collection as a synchronized collection.
* Asynchronized Collection: Begin a thread to access each element of a list. It's appropriate to use when the access of each element of collection consumes some time. Use the operation `asyncCollection()` to work with your collection as a synchronized collection.

To use Buzz, you need to use one of these handlers. 

##each()

```each``` is the most basic operations of Buzz. You could use it to iterate a collections. See the example below:
```java
List<Integer> numbers = Arrays.asList(0,1,2,3,4,5);
collection(numbers).each(new Function<Integer>(){
  public void block(Integer i){ System.out.println(i); }
});
```
It's not a huge difference from ```for```, ```while``` and ```for each``` Java iteration operations, but if you can build some reusable functions to iterate the collection, we could see some better results than the example above. 
```java
Function print = new Function(){
  public void block(Object i){ System.out.println(i); }
});

// You could use print function to others collections
collection(numbers).each(print);
collection(numbers2).each(print);
```

That is where live the power of ```each``` operations: you could use functions objects to iterate some collection. It's like the Java 8 Lambdas. 

##filter()

```filter``` is an operation used to get a subcollection of a collection based on a boolean condition. The most basic ```filter``` operation can be made using a ```Conditional``` object like the example below.

```java
// Filter the list and get a sublist with elements less than 2
Collection<Integer> subList = collection(numbers).filter(
  new Conditional<Integer>(){public boolean block(Integer t) { return t < 2; }}
).get();	
```

The result doesn't sound good, and the code above is very wordy. Buzz provides some matchers that could give us better results in terms of expressivity. The matchers are some premade conditionals which could attend some specific necessity. Let's see how good the code became using Buzz Matchers.  

```java
// Filter the list and get a sublist with elements less than 2
Collection<Integer> subList = collection(numbers).filter(lessThan(50)).get();	
```

The Matchers are added to the code through the import:

```java
import static com.github.adrianobrito.buzz.Matchers.*;
```

##Matchers

Now, we will explain each Matcher provided by Buzz DSL with examples.

### greaterThan()
```java
Collection<Integer> subList = collection(numbers).filter(greaterThan(2)).get();
```
### lessThan()
```java
Collection<Integer> subList = collection(numbers).filter(lessThan(2)).get();
```
### greaterEqualsThan()
```java
Collection<Integer> subList = collection(numbers).filter(greaterEqualsThan(2)).get();
```
### lessEqualsThan()
```java
Collection<Integer> subList = collection(numbers).filter(lessEqualsThan(2)).get();
```
### equalsTo()
```java
Collection<Integer> subList = collection(numbers).filter(equalsTo(2)).get();
```
### notEqualsTo()
```java
Collection<Integer> subList = collection(numbers).filter(notEqualsTo(2)).get();
```
### containsString()
```java
Collection<String> strings = Arrays.asList("Adriano", "Clara", "Tito", "Isauro");
Collection<String> subList = collection(strings).filter(containsString("Adriano")).get();
```

### before()
```java
Collection<Date> dates = Arrays.asList(...);
Collection<Date> subList = collection(dates).filter(before(new Date())).get();
```

### after()
```java
Collection<Date> dates = Arrays.asList(...);
Collection<Date> subList = collection(dates).filter(afterDate())).get();
```

### matches()
This matcher is very powerful and can fit a large scope of cases of filtering operations. You use one object to do a query based on its not null properties. See the example below.
```java
public class Person{
	private String name;
	private Integer age;
	
	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	...
}

Person person = new Person(null, 18);
// Query a sublist of persons who has 18 years old
Collection<String> subList = collection(strings).filter(containsString("Adriano")).get();
```

### propertyMatches()
This is other powerful matchers, which allow you to do a conditional on the properties of an object.

We will begin rewriting the same example listed before with the ```matches``` Matcher.
```java
Collection<String> subList = collection(dataList).filter(propertyMatches("idade", 18)).get();
```
You can use another conditional to filter the listed elements by appying a conditional, like this:
```java
Collection<String> subList = collection(dataList).filter(propertyMatches("idade", greaterThan(18))).get();
```
##Nested Filtering

You can do nested filters to query data from your sublist. See the example below which lists all persons who has age between 18 and 50:
```java
Collection<String> subList = collection(dataList).filter(propertyMatches("idade", greaterThan(18)))
						 .filter(propertyMatches("idade", lessThan(50))
						 .get();
```

##Download

You can download the Beta Release [here](https://github.com/adrianobrito/buzz/buzz.jar).
