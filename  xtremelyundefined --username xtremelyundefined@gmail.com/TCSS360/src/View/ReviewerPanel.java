import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;


public class ReviewerPanel extends JPanel{
	private String Reviewer = "JOE SCHMOE";

	public ReviewerPanel(){
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(), bottomPanel = new JPanel(), midPanel = new JPanel();

		AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,2,16,0);


		JLabel reviewer = new JLabel(Reviewer);
		JLabel logout = new JLabel("Logout");
		logout.setBorder(brdr);
		JPanel topInside = new JPanel();
		topInside.setLayout(new GridLayout(2,1));
		topInside.add(new JLabel("MSEE Conference"));
		topInside.add(new JLabel("Tracking System"));

		topPanel.setLayout(new GridLayout(1,3));
		topPanel.add(topInside);
		topPanel.add(reviewer);
		topPanel.add(logout);
		topPanel.setSize(800, 100);
		midPanel.setSize(800,600);

		topPanel.setBorder(brdr);
		topPanel.setOpaque(false);

		midPanel.setLayout(null);
		bottomPanel.setBorder(brdr);
		bottomPanel.setOpaque(false);
		bottomPanel.setSize(400, 400);
		bottomPanel.setBounds(100, 50, 600, 500);
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);

		midPanel.add(bottomPanel);

	}

}
