package com.bridgelabz.addressbookstreams;
//Uc10
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class MultipleAddressBook {
	private Map<String,MultipleContacts> addressBookMap,cityMap,stateMap;
	private MultipleContacts addressb,cityb,stateb;
	Scanner sc;
	String states;
	public MultipleAddressBook() {
		addressBookMap=new HashMap<>();
		cityMap=new HashMap<>();
		stateMap=new HashMap<>();
		addressb=new MultipleContacts();
		cityb=null;
		stateb=null;
		sc=new Scanner(System.in);
	}
	public int displayChoice() {
		System.out.println("1.Add new Address Book");
		System.out.println("2.View Address Book List");
		System.out.println("3.Search Person By City");
		System.out.println("4.Search Person By State");
		System.out.println("5.Count Contacts by city");
		System.out.println("6.Count Contacts by State");
		System.out.println("7.Quit");
		System.out.println("Enter choice from 1 and 7");
		int choice=sc.nextInt();
		return choice;
	}
	public void addNewAddressBook() {
		System.out.println("Enter Name For Address Book");
		String aName=sc.next();
		aName=aName.toLowerCase();
		Contacts cc=addressb.addContact();
		addressBookMap.put(aName, addressb);
		if(cityMap.containsKey(cc.city)) {
			cityb.contactArrayList.add(cc);
			cityMap.put(cc.city, cityb);
		} else {
			cityb=new MultipleContacts();
			cityb.contactArrayList.add(cc);
			cityMap.put(cc.city, cityb);
		}
		if(stateMap.containsKey(cc.state)) {
			stateb.contactArrayList.add(cc);
			stateMap.put(cc.state, stateb);
		} else {
			stateb=new MultipleContacts();
			stateb.contactArrayList.add(cc);
			stateMap.put(cc.state, stateb);
		}
	}
	public void viewAddressBookList() {
		for(Map.Entry<String,MultipleContacts> m:addressBookMap.entrySet()) {
			System.out.println(m.getKey());
		}
	}
	public void searchContactByCityName() {
		System.out.println("Enter name of City to search contact");
		String city=sc.next();
		city=city.toLowerCase();
		if(cityMap.containsKey(city)) {
			MultipleContacts list=cityMap.get(city);
			list.showAllContacts();
		}
	}
	Predicate<Contacts> search=c->c.state.equalsIgnoreCase(states);
	public void searchContactByStateName() {
		System.out.println("Enter name of State to search contact");
		states=sc.next();
		states=states.toLowerCase();
		MultipleContacts nc=new MultipleContacts();
		for(Map.Entry<String,MultipleContacts> m:addressBookMap.entrySet()) {
			if(m.getValue().contactArrayList.size()>0) {
				ArrayList<Contacts> stateWiseList=(ArrayList<Contacts>) m.getValue().contactArrayList.stream()
						  	  					   .filter(search)
						  	  					   .collect(Collectors.toList());
				Iterator<Contacts> i=stateWiseList.iterator();
				while(i.hasNext()) {
					Contacts c=(Contacts) i.next();
					nc.contactArrayList.add(c);
				}
			}
		}
		nc.showAllContacts();
	}
	public void viewContactByState() {
		System.out.println("Enter name of State to search contact");
		String state=sc.next();
		state=state.toLowerCase();
		if(stateMap.containsKey(state)) {
			MultipleContacts list=stateMap.get(state);
			list.showAllContacts();
		}
	}
	public void countByCity() {
		for(Map.Entry<String,MultipleContacts> m:cityMap.entrySet()) {
			System.out.println("Count for "+m.getKey()+" is "+m.getValue().contactArrayList.size());
		}
	}
	public void countByState() {
		for(Map.Entry<String,MultipleContacts> m:stateMap.entrySet()) {
			System.out.println("Count for "+m.getKey()+" is "+m.getValue().contactArrayList.size());
		}
	}
	public static void main(String[] args) {
		MultipleAddressBook mab=new MultipleAddressBook();
		int choice;
		int flag=0;
		while(flag==0) {
			choice=mab.displayChoice();
			switch(choice) {
				case 1:
					mab.addNewAddressBook();
					break;
				case 2:
					mab.viewAddressBookList();
					break;
				case 3:
					mab.searchContactByCityName();
					break;
				case 4:
					mab.viewContactByState();
					break;
				case 5:
					mab.countByCity();
					break;
				case 6:
					mab.countByState();
					break;
				case 7:
					flag=1;
					break;
				default:
					System.out.println("You have Entered Wrong Choice.Please enter option between 1 to 7");
			}
		}
	}
}
