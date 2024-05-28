package emploiDuTemps_java;
/*Code �crit du 26 au 28 Mars 2021 � N'djam�na au Tchad par TARGOTO CHRISTIAN
 * Contact: ct@chrislink.net / 23560316682*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class Requetes extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbclasse,lbmatiere,lbtitre,lbtitre2,lbid,lbclasse2;
	JTextField tfmatiere,tfid;
	JComboBox comboclasse,comboclasse2;
	JButton btrech,btsupp,btrech2;
	public Requetes(){
		this.setTitle("chcode_appli");
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		getContentPane().add(pn);
		pn.setBackground(new Color(192, 192, 192));
		
		lbtitre=new JLabel("Les séances de cours dans la semaine d'une matiére dans une classe");
		lbtitre.setHorizontalAlignment(SwingConstants.CENTER);
		lbtitre.setBounds(84,10,639,30);
		lbtitre.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre);
		
		lbtitre2=new JLabel("Emploi du temps de la semaine par classe");
		lbtitre2.setBounds(147,375,405,30);
		lbtitre2.setFont(new Font("Arial",Font.BOLD,18));
		pn.add(lbtitre2);
		
		//classe
		lbclasse=new JLabel("Classe");
		lbclasse.setBounds(127,40,150,25);
		lbclasse.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbclasse);
		
		comboclasse=new JComboBox();
		comboclasse.addItem("");
		comboclasse.addItem("6eme");
		comboclasse.addItem("5eme");
		comboclasse.addItem("4eme");
		comboclasse.addItem("3eme");
		comboclasse.addItem("2nde L");
		comboclasse.addItem("2dne S");
		comboclasse.addItem("1ere L");
		comboclasse.addItem("1ere S");
		comboclasse.addItem("TA");
		comboclasse.addItem("TD");
		comboclasse.addItem("TC");
		comboclasse.setBounds(127,70,150,25);
		pn.add(comboclasse);
		//classe2
				lbclasse2=new JLabel("Classe");
				lbclasse2.setBounds(157,415,150,25);
				lbclasse2.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbclasse2);
				
				comboclasse2=new JComboBox();
				comboclasse2.addItem("");
				comboclasse2.addItem("6eme");
				comboclasse2.addItem("5eme");
				comboclasse2.addItem("4eme");
				comboclasse2.addItem("3eme");
				comboclasse2.addItem("2nde L");
				comboclasse2.addItem("2dne S");
				comboclasse2.addItem("1ere L");
				comboclasse2.addItem("1ere S");
				comboclasse2.addItem("TA");
				comboclasse2.addItem("TD");
				comboclasse2.addItem("TC");
				comboclasse2.setBounds(227,415,150,25);
				pn.add(comboclasse2);
		//matiere
		lbmatiere=new JLabel("Matiére");
		lbmatiere.setBounds(297,40,150,25);
		lbmatiere.setFont(new Font("Arial",Font.BOLD,16));
		pn.add(lbmatiere);
		
		tfmatiere=new JTextField();
		tfmatiere.setBounds(297,70,150,25);
		pn.add(tfmatiere);
		//id
		
				lbid=new JLabel("ID");
				lbid.setBounds(20,340,150,25);
				lbid.setFont(new Font("Arial",Font.BOLD,16));
				pn.add(lbid);
				
				tfid=new JTextField();
				tfid.setBounds(50,340,90,25);
				pn.add(tfid);
			//bouton supprimer
				btsupp=new JButton("SUP");
				Image img_sup= new ImageIcon(this.getClass().getResource("/delete.png") ).getImage(); 
				btsupp.setIcon(new ImageIcon(img_sup));
				btsupp.setBounds(150,340,110,25);
				btsupp.setBackground(new Color(255, 0, 0));
				btsupp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String id=tfid.getText();
						String rq="delete from tb_cours where id='"+id+"'";
						try{
							st=con.laConnection().createStatement();
		if(JOptionPane.showConfirmDialog(null,"voulez vous Supprimer? ",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
							 st.executeUpdate(rq);
							 JOptionPane.showMessageDialog(null,"Suppr�ssion �ffectu�e avec succ�s !",null,JOptionPane.INFORMATION_MESSAGE);
		}
						}
						catch(SQLException ex){
					    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
					    }
						
					}
					
				});
				pn.add(btsupp);
		//bouton recherche
		btrech=new JButton("CHER");
		Image img_ch= new ImageIcon(this.getClass().getResource("/search2.png") ).getImage(); 
		btrech.setIcon(new ImageIcon(img_ch));
		btrech.setBounds(457,70,120,25);
		btrech.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				String classe=comboclasse.getSelectedItem().toString(),
						matiere=tfmatiere.getText();
				DefaultTableModel df2=new  DefaultTableModel();
				  init2();
				  pn.add(scroll2);
				 df2.addColumn("ID");
				 df2.addColumn("Classe");
				 df2.addColumn("Matiere");
				 df2.addColumn("Jour");
				 df2.addColumn("Heure");
				 df2.addColumn("Nom enseignant");
				 df2.addColumn("Contact enseignant");
				 table2.setModel(df2);
				 String rq2="select * from enseignant_cours where classe='"+classe+"' and matiere='"+matiere+"' order by num_jour";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq2);
					 while(rst.next()){
						 df2.addRow(new Object[]{
		rst.getString("id"),rst.getString("classe"),rst.getString("matiere"),rst.getString("jour"),rst.getString("heure"),
		rst.getString("nom"),rst.getString("contact")
								 });
						 
					   } 
					 }
					 
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
				
			}
		});
		pn.add(btrech);
		init2();
		DefaultTableModel dfs=new  DefaultTableModel();
		  pn.add(scroll2);
		 dfs.addColumn("ID");
		 dfs.addColumn("Classe");
		 dfs.addColumn("Matiere");
		 dfs.addColumn("Jour");
		 dfs.addColumn("Heure");
		 dfs.addColumn("Nom enseignant");
		 dfs.addColumn("Contact enseignant");
		 table2.setModel(dfs);
		
		//btrech2
		//bouton recherche
				btrech2=new JButton("CHER");
				Image img_ch2 = new ImageIcon(this.getClass().getResource("/search2.png") ).getImage(); 
				 btrech2.setIcon(new ImageIcon(img_ch2));
				btrech2.setBounds(387,415,120,25);
				btrech2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String classe=comboclasse2.getSelectedItem().toString();
						DefaultTableModel df2=new  DefaultTableModel();
						  init2();
						  pn.add(scroll2);
						 df2.addColumn("ID");
						 df2.addColumn("Classe");
						 df2.addColumn("Jour");
						 df2.addColumn("Matiere");
						 df2.addColumn("Heure");
						 df2.addColumn("Nom enseignant");
						 df2.addColumn("Contact enseignant");
						 table2.setModel(df2);
						 String rq2="select * from enseignant_cours where classe='"+classe+"' order by num_jour,heure";
						 try{
							 st=con.laConnection().createStatement();
							 rst=st.executeQuery(rq2);
							 while(rst.next()){
								 df2.addRow(new Object[]{
				rst.getString("id"),rst.getString("classe"),rst.getString("jour"),rst.getString("matiere"),rst.getString("heure"),
				rst.getString("nom"),rst.getString("contact")
										 });
								 
							   } 
							 }
							 
						 catch(SQLException ex){
						    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
						    }
						
					}
				});
				pn.add(btrech2);
		
	}
	private void init2(){
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setBounds(10,120,770,200);
		scroll2.setViewportView(table2);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Requetes rq=new Requetes();
	rq.setVisible(true);

	}

}
