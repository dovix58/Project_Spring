package com.example.springproject.mappers;

public interface Mapper<A,B> {

    B mapTo(A a);

    A mapfrom(B b);
}
