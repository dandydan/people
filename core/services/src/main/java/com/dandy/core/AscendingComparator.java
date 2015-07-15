package com.dandy.core;

import java.util.Comparator;
import com.dandy.core.Person;

public class AscendingComparator implements Comparator<Person>{

    @Override
    public int compare(Person person1, Person person2) {
	return Float.compare(person1.getGwa(), person2.getGwa());
    }
}
