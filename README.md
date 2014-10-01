# Buzz DSL

Buzz is a DSL for that who doesn't can use Java 8 new features for Collections, but wants to have some expressive way to handle Java Collections. The DSL provide intuitive elements for select and query data from any collection. Let's see the main features.

First of all, you need to put two required imports on your Java File to use Buzz DSL.
```java
import static com.github.adrianobrito.buzz.Buzz.*;
import static com.github.adrianobrito.buzz.Matchers.*;
```

##Collections Handlers

You have 3 ways of handle a collection on Buzz:

* Basic Collection: Handles the collection as a basic collection that runs on a Single Thread. Use the operation `collection()` to work with yout collection as a basic collection.
* Synchronized Collection: Handles the collection as a Thread-Safe collection, and locks an element when it is acessed.  Use the operation `syncCollection()` to work with yout collection as a synchronized collection.
* Asynchronized Collection: Begin a thread to access each element of a list. It's appropriate to use when the access of each element of collection consumes some time. Use the operation `asyncCollection()` to work with yout collection as a synchronized collection.

To use Buzz, you need to use one of these handlers. 

##each()

Each is one of the basic operations of Buzz. You could use it to iterate a collections. See the example below:
```java
List<Integer> numbers = Arrays.asList(0,1,2,3,4,5);
collection(numbers).each(new Function<Integer>(){
  public void block(Integer i){ System.out.println(i); }
});
```
It's not a very great difference from ```for```, ```while``` and ```for each``` Java iteration operations, but if you can build some reusable functions to iterate the collection, we could see some better results than the example above. 
```java
Function<Object> print = new Function<Integer>(){
  public void block(Integer i){ System.out.println(i); }
});

// You could use print function to others collections
collection(numbers).each(print);
collection(numbers2).each(print);
```

That is where live the power of ```each``` operations: you could use functions objects to iterate some collection. It's like the Java 8 Lambdas. 
