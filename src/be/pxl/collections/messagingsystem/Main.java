package be.pxl.collections.messagingsystem;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		try {
			Channel crap = new Channel("Crap", "Testing Testing");
			Follower f1 = new Follower("f1", "f1@memail.com");
			f1.setCity("Bree");
			f1.setDateOfBirth(LocalDate.of(2010, 11, 10));
			Follower f2 = new Follower("f2", "f2@memail.com");
			f2.setCity("Genk");
			f2.setDateOfBirth(LocalDate.of(1996, 12, 14));
			Follower f3 = new Follower("f3", "f3@memail.com");
			f3.setCity("Hasselt");
			f3.setDateOfBirth(LocalDate.of(2016, 8, 14));
			Follower f4 = new Follower("f4", "f4@memail.com");
			f4.setCity("Hasselt");
			f4.setDateOfBirth(LocalDate.of(1996, 1, 8));
			Follower f5 = new Follower("f5", "f5@memail.com");
			// Follower dupF1 = new Follower("f1", "f1@memail.com");
			// Follower dupF2 = new Follower("f12", "f2@memail.com");
			crap.addFollower(f1);
			crap.addFollower(f2);
			crap.addFollower(f4);
			crap.addFollower(f5);
			crap.addFollower(f3);
			// crap.addFollower(dupF1);
			// crap.addFollower(dupF2);
			crap.getFollowers().stream().sorted((fol1, fol2) -> fol1.getNick().compareTo(fol2.getNick()))
					.forEach(System.out::println);
			crap.getFollowers().stream().filter(f -> f.getDateOfBirth() != null)
					.sorted((fol1, fol2) -> fol1.getDateOfBirth().compareTo(fol2.getDateOfBirth()))
					.collect(Collectors.toCollection(LinkedList<Follower>::new)).descendingIterator()
					.forEachRemaining(System.out::println);
			crap.publishMessageEveryone(new Message("Welcome", "%NICK% welcome to the test!"));
			crap.publishMessage(new Message("Korting zwembad", "Korting bij het zwembad van 5%"),
					(f -> f.getCity() != null && f.getCity().equals("Hasselt")));
			crap.removeFollower("f3");
			crap.publishMessage(new Message("Happy bday", "Happy bday %NICK%!"),
					((Follower f) -> f.getDateOfBirth() != null
							&& f.getDateOfBirth().equals(LocalDate.now().withYear(f.getDateOfBirth().getYear()))));
		} catch (MessageSystemException e) {
			e.printStackTrace();
		}

	}

}
