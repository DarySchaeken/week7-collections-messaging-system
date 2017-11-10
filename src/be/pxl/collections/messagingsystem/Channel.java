package be.pxl.collections.messagingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Channel {
	private String name;
	private String description;
	private List<Follower> followers;

	public Channel(String name, String description) throws MessageSystemException {
		this.followers = new ArrayList<Follower>();

		if (name == null || name.equals("")) {
			MessageSystemException messageSystemException = new MessageSystemException(
					ValidationCode.MISSING_REQUIRED_FIELD);
			messageSystemException.addField("missing_field", "name");
			throw messageSystemException;
		}

		this.name = name;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void removeFollower(String nickOrEmail) {
		Follower follower = getFollowerByEmail(nickOrEmail);
		if (follower != null) {
			followers.remove(follower);
		}
		follower = getFollowerByNick(nickOrEmail);
		if (follower != null) {
			followers.remove(follower);
		}
	}

	public void addFollower(Follower follower) throws MessageSystemException {
		if (followers.stream().filter(f -> f.getNick().equals(follower.getNick())).count() > 0) {
			MessageSystemException messageSystemException = new MessageSystemException(
					ValidationCode.DUPLICATE_FOLLOWER);
			messageSystemException.addField("nick", follower.getNick());
			throw messageSystemException;
		}
		if (followers.stream().filter(f -> f.getEmail().equals(follower.getEmail())).count() > 0) {
			MessageSystemException messageSystemException = new MessageSystemException(
					ValidationCode.DUPLICATE_FOLLOWER);
			messageSystemException.addField("email", follower.getEmail());
			throw messageSystemException;
		}
		followers.add(follower);
	}

	public List<Follower> getFollowers() {
		return followers;
	}

	public void publishMessageEveryone(Message message) {
		followers.stream().forEach(f -> sendMessage(message, f));
	}

	public void publishMessage(Message message, Predicate<Follower> predicate) {
		followers.stream().filter(predicate).forEach(f -> sendMessage(message, f));
	}

	private Follower getFollowerByNick(String nick) {
		return followers.stream().filter(f -> f.getNick().equals(nick)).findFirst().orElse(null);
	}

	private Follower getFollowerByEmail(String email) {
		return followers.stream().filter(f -> f.getEmail().equals(email)).findFirst().orElse(null);
	}

	private void sendMessage(Message message, Follower follower) {
		MailService.getInstance().sendMessage(message, follower);
	}

}
