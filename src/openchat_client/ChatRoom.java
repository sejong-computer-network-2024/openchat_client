package openchat_client;

import java.util.LinkedList;
import java.util.List;

public class ChatRoom {
	private String id;
	private String title;
	private List<Chat> chatList = new LinkedList<Chat>();
	
	public ChatRoom(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	
}
