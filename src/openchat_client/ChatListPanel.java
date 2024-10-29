package openchat_client;

import java.util.LinkedList;
import java.util.List;

public class ChatListPanel extends ClientPanel {

	public final List<ChatListPanel> ChatPanelList = new LinkedList<>();
	
	public ChatListPanel(ClientFrame frame) {
		super(frame);
	}

}
