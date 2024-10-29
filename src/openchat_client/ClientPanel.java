package openchat_client;

import javax.swing.JPanel;

public abstract class ClientPanel extends JPanel {
	protected ClientFrame frame;
	public ClientPanel(ClientFrame frame) {
		super();
		this.frame = frame;
	}
}
