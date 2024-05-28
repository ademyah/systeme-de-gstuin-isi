package emploiDuTemps_java;
import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout;
    private JPanel mainPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 560);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JButton btnNewButton = new JButton("Start");
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 26));
        btnNewButton.setBackground(new Color(128, 128, 128));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Cours frame
                Cours cr = new Cours();
                cr.setVisible(true);
            }
        });
        btnNewButton.setBounds(356, 316, 271, 85);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("   Gestionnaire du emploi du temps");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel.setBounds(330, 41, 339, 59);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        ImageIcon img_1= new ImageIcon(this.getClass().getResource("/Welcome.png") ); 
        lblNewLabel_1.setIcon(img_1);
        lblNewLabel_1.setBounds(173, 110, 622, 184);
        contentPane.add(lblNewLabel_1);
       
        JLabel lblNewLabel_2 = new JLabel("");
        ImageIcon img_2= new ImageIcon(this.getClass().getResource("/isi.png") ); 
        lblNewLabel_2.setIcon(img_2);
        lblNewLabel_2.setBounds(10, 388, 243, 125);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        ImageIcon img_3= new ImageIcon(this.getClass().getResource("/uu.png") ); 
        lblNewLabel_3.setIcon(img_3);
        lblNewLabel_3.setBounds(840, 394, 112, 129);
        contentPane.add(lblNewLabel_3);
    }
}
