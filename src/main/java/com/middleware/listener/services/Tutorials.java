package com.middleware.listener.services;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tutorials {

	private List<Tutorial> tutorial;

	public List<Tutorial> getTutorial() {
		return tutorial;
	}

	@XmlElement
	public void setTutorial(List<Tutorial> tutorial) {
		this.tutorial = tutorial;
	}


//	
//	public void returnIterator() { 
//	// Iterator - Returns an iterator over the elements in this list in proper sequence.
//    System.out.println("\n==============> 3. Iterator Example...");
//    Iterator<String> crunchifyIterator = crunchifyList.iterator();
//    while (crunchifyIterator.hasNext()) {
//        System.out.println(crunchifyIterator.next());
//    }
//	}
//    // ListIterator - traverse a list of elements in either forward or backward order
//    // An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during iteration,
//    // and obtain the iterator's current position in the list.
//    System.out.println("\n==============> 4. ListIterator Example...");
//    ListIterator<String> crunchifyListIterator = crunchifyList.listIterator();
//    while (crunchifyListIterator.hasNext()) {
//        System.out.println(crunchifyListIterator.next());
//    }

}