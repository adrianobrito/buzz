# Buzz DSL

Buzz is a tool for that who doesn't can use Java 8 and features for Collections, but wants to have some expressive way to handle Java Collections. The DSL provide intuitive elements for select and query data from any collection. Let's see the main features.

Collections Handlers
==========

You have 3 ways of handle a collection on Buzz:

* Basic Collection: Handles the collection as a basic collection that runs on a Single Thread. Use the operation `collection()` to work with yout collection as a basic collection.
* Synchronized Collection: Handles the collection as a Thread-Safe collection, and locks an element when it is acessed.  Use the operation `syncCollection()` to work with yout collection as a synchronized collection.
* Asynchronized Collection: Begin a thread to access each element of a list. It's appropriate to use when the access of each element of collection consumes some time. Use the operation `asyncCollection()` to work with yout collection as a synchronized collection.
