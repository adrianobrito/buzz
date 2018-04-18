package com.github.adrianobrito.buzz.operations;

/**
 * Created by adrianos on 29/04/2015.
 */
public interface BQLOperations<T> {

    CollectionOperations<T> where(String bql);

}
