package emploiDuTemps_java;
/*Code �crit du 26 au 28 Mars 2021 � N'djam�na au Tchad par TARGOTO CHRISTIAN
 * Contact: ct@chrislink.net / 23560316682*/
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
public class Cours extends JFrame {
	Statement st;
	Conneccion con=new Conneccion();
	ResultSet rst;
	JTable table,table2;
	JScrollPane scroll,scroll2;
	JLabel lbtitre,lbtitre2;
	JButton btenrg,btmodif,btsupp,btenrg2,btreq;
	private JTextField tfcontact;
	private JTextField tfnom;
	private JTextField tfmatricule;
	private JPanel panel_1;
	private JTextField tfmatiere;
	private JTextField tfmatri_ens;
	public Cours(){
		this.setTitle("Emploi Du Temps");
		this.setSize(1100,659);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JPanel pn=new JPanel();
		pn.setLayout(null);
		getContentPane().add(pn);
		pn.setBackground(new Color(192, 192, 192));
		

		lbtitre=new JLabel("Formulaire d'enregistrement des enseignants");
		lbtitre.setHorizontalAlignment(SwingConstants.CENTER);
		lbtitre.setBounds(311,10,467,41);
		lbtitre.setFont(new Font("Arial", Font.BOLD, 20));
		pn.add(lbtitre);
		
		lbtitre2=new JLabel("Formulaire d'enregistrement des séances de cours");
		lbtitre2.setHorizontalAlignment(SwingConstants.CENTER);
		lbtitre2.setBounds(278,281,507,35);
		lbtitre2.setFont(new Font("Arial", Font.BOLD, 20));
		pn.add(lbtitre2);
				
		
		//bouton enregistrement enseignant
				btenrg=new JButton("ENR");
				btenrg.setHorizontalAlignment(SwingConstants.LEFT);
				btenrg.setForeground(new Color(0, 0, 0));
				btenrg.setBackground(new Color(0, 128, 128));
				Image img_en= new ImageIcon(this.getClass().getResource("/save.png") ).getImage(); 
				 btenrg.setIcon(new ImageIcon(img_en));
				btenrg.setBounds(454,218,131,25);
	btenrg.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ev){
			String matricule,nom,contact;
			matricule= tfmatricule.getText();
			nom=tfnom.getText();
			contact=tfcontact.getText();
			String rq1="insert into tb_enseignant(matricule,nom,contact) values('"+matricule+"','"+nom+"','"+contact+"')";
			try{
				st=con.laConnection().createStatement();
				if(!matricule.equals("")&&!nom.equals("")&&!contact.equals("")){
				st.executeUpdate(rq1);
	    		JOptionPane.showMessageDialog(null,"Insertion reussie!",null,JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null,"Completez le formulaire!",null,JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
		    }
			dispose();
			Cours crs=new Cours();
			crs.setVisible(true);
		}
		
	});
				pn.add(btenrg);
		//bouton modification enseignant
				btmodif=new JButton("MOD");
				btmodif.setBackground(new Color(255, 255, 255));
				btmodif.setHorizontalAlignment(SwingConstants.LEFT);
				Image img_up= new ImageIcon(this.getClass().getResource("/update.png") ).getImage(); 
				 btmodif.setIcon(new ImageIcon(img_up));
				btmodif.setBounds(238,183,120,25);
				btmodif.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String matricule=tfmatricule.getText(),
								nom=tfnom.getText(),
								contact=tfcontact.getText();
			String rq="update tb_enseignant set nom='"+nom+"',contact='"+contact+"' where matricule='"+matricule+"'";
			try{
				st=con.laConnection().createStatement();
				if(!matricule.equals("")&&!nom.equals("")&&!contact.equals("")){
				st.executeUpdate(rq);
	    		JOptionPane.showMessageDialog(null,"Modification reussie!",null,JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null,"Completez le formulaire!",null,JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
		    }	
			dispose();
			Cours crs=new Cours();
			crs.setVisible(true);	}
				});
				pn.add(btmodif);
		//bouton suppr�ssion enseignant
				btsupp=new JButton("SUPP");
				btsupp.setHorizontalAlignment(SwingConstants.LEFT);
				btsupp.setForeground(new Color(0, 0, 0));
				btsupp.setBackground(new Color(255, 0, 0));
				Image img_sup= new ImageIcon(this.getClass().getResource("/delete.png") ).getImage(); 
				 btsupp.setIcon(new ImageIcon(img_sup));
				btsupp.setBounds(454,183,131,25);
				btsupp.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						String matricule=tfmatricule.getText();
					
			String rq="delete from tb_enseignant where matricule='"+matricule+"'";
			try{
				st=con.laConnection().createStatement();
				if(JOptionPane.showConfirmDialog(null,"Voulez vous supprimer? ",null,JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				st.executeUpdate(rq);
	    		JOptionPane.showMessageDialog(null,"Suppréssion reussie!",null,JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			catch(SQLException ex){
		    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
		    }	
			dispose();
			Cours crs=new Cours();
			crs.setVisible(true);	}
				});
				pn.add(btsupp);
				//bouton enregistrement s�ance cours
				
				//bouton pour afficher l'interface des requetes
				btreq=new JButton("SET");
				btreq.setHorizontalAlignment(SwingConstants.LEFT);
				Image img_set= new ImageIcon(this.getClass().getResource("/settings.png") ).getImage(); 
				btreq.setIcon(new ImageIcon(img_set));
				btreq.setBackground(new Color(255, 255, 255));
				btreq.setBounds(465,535,120,25);
				btreq.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ev){
						Requetes rq=new Requetes();
						rq.setVisible(true);
					}
				});
				pn.add(btreq);
				
				///liste enseignant
				 DefaultTableModel df=new  DefaultTableModel();
				  init();
				  pn.add(scroll);
				 df.addColumn("Matricule");
				 df.addColumn("Nom");
				 df.addColumn("Contact");
				 table.setModel(df);
				 String rq="select * from tb_enseignant order by nom";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq);
					 while(rst.next()){
						 df.addRow(new Object[]{
		rst.getString("matricule"),rst.getString("nom"),rst.getString("contact")
								 });
						 
					   } 
					 }
					 
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
				 ///liste s�ance cours
				 DefaultTableModel df2=new  DefaultTableModel();
				  init2();
				  pn.add(scroll2);
				 df2.addColumn("Classe");
				 df2.addColumn("Matiere");
				 df2.addColumn("Jour");
				 df2.addColumn("Heure");
				 df2.addColumn("Enseignant");
				 table2.setModel(df2);
				 
				 JPanel panel = new JPanel();
				 panel.setBounds(238, 61, 347, 112);
				 pn.add(panel);
				 panel.setLayout(null);
				 
				 tfcontact = new JTextField();
				 tfcontact.setBounds(103, 70, 218, 25);
				 panel.add(tfcontact);
				 
				 JLabel lbcontact = new JLabel("Contact");
				 lbcontact.setFont(new Font("Arial", Font.BOLD, 16));
				 lbcontact.setBounds(20, 68, 100, 25);
				 panel.add(lbcontact);
				 
				 JLabel lbnom = new JLabel("Nom");
				 lbnom.setFont(new Font("Arial", Font.BOLD, 16));
				 lbnom.setBounds(20, 38, 100, 25);
				 panel.add(lbnom);
				 
				 tfnom = new JTextField();
				 tfnom.setBounds(103, 40, 218, 25);
				 panel.add(tfnom);
				 
				 JLabel lbmatricule = new JLabel("Matricule");
				 lbmatricule.setFont(new Font("Arial", Font.BOLD, 16));
				 lbmatricule.setBounds(20, 10, 170, 25);
				 panel.add(lbmatricule);
				 
				 tfmatricule = new JTextField();
				 tfmatricule.setBounds(103, 10, 100, 25);
				 panel.add(tfmatricule);
				 
				 JButton btrech = new JButton("Chercher");
				 
				 btrech.addActionListener(new ActionListener() {
				 	public void actionPerformed(ActionEvent e) {
				 		String matricule=tfmatricule.getText(),
								nom=tfnom.getText(),
								contact=tfcontact.getText();
						//nom like '%$rech%'
				String rq="select * from tb_enseignant where nom like '%"+nom+"%'";
				try{
					st=con.laConnection().createStatement();
					rst=st.executeQuery(rq);
					if(rst.next()){
						
						tfmatricule.setText(rst.getString("matricule"));
						tfnom.setText(rst.getString("nom"));
						tfcontact.setText(rst.getString("contact"));
							
					}
					else{
						JOptionPane.showMessageDialog(null,"Enregistrement inexistant!",null,JOptionPane.ERROR_MESSAGE);
					}
							
					
				}
				catch(SQLException ex){
			    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
			    }
						
					
				 	}
				 });
				 Image img_ch= new ImageIcon(this.getClass().getResource("/search2.png") ).getImage(); 
				 btrech.setIcon(new ImageIcon(img_ch));
				 btrech.setBounds(205, 10, 116, 25);
				 panel.add(btrech);
				 
				 
				 JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("image");
				 ImageIcon img_1= new ImageIcon(this.getClass().getResource("/teacher_img.png") ); 
				 lblNewJgoodiesLabel.setIcon(img_1);
				 lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
				 lblNewJgoodiesLabel.setBounds(20, 21, 198, 201);
				 pn.add(lblNewJgoodiesLabel);
				 
				 Component horizontalStrut = Box.createHorizontalStrut(60);
				 horizontalStrut.setEnabled(false);
				 horizontalStrut.setForeground(new Color(0, 0, 0));
				 horizontalStrut.setFont(new Font("Arial Black", Font.PLAIN, 12));
				 horizontalStrut.setBackground(new Color(0, 0, 0));
				 horizontalStrut.setBounds(0, 271, 1086, 8);
				 pn.add(horizontalStrut);
				 
				 Component horizontalStrut_1 = Box.createHorizontalStrut(20);
				 horizontalStrut_1.setBounds(550, 271, 526, 1);
				 pn.add(horizontalStrut_1);
				 
				 panel_1 = new JPanel();
				 panel_1.setBounds(238, 347, 347, 180);
				 pn.add(panel_1);
				 panel_1.setLayout(null);
				 
				 JLabel lbclasse = new JLabel("Classe");
				 lbclasse.setFont(new Font("Arial", Font.BOLD, 16));
				 lbclasse.setBounds(10, 8, 150, 25);
				 panel_1.add(lbclasse);
				 
				 JComboBox comboclasse = new JComboBox();
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
				 comboclasse.setBounds(173, 10, 150, 25);
				 panel_1.add(comboclasse);
				 
				 JLabel lbmatiere = new JLabel("Matiére");
				 lbmatiere.setFont(new Font("Arial", Font.BOLD, 16));
				 lbmatiere.setBounds(10, 38, 150, 25);
				 panel_1.add(lbmatiere);
				 
				 tfmatiere = new JTextField();
				 tfmatiere.setBounds(173, 40, 150, 25);
				 panel_1.add(tfmatiere);
				 
				 JLabel lbjour = new JLabel("Jour");
				 lbjour.setFont(new Font("Arial", Font.BOLD, 16));
				 lbjour.setBounds(10, 70, 150, 25);
				 panel_1.add(lbjour);
				 
				 JComboBox combojour = new JComboBox();
				 combojour.setBounds(173, 72, 150, 25);
				 combojour.addItem("");
				 combojour.addItem("LUNDI");
				 combojour.addItem("MARDI");
				 combojour.addItem("MERCREDI");
				 combojour.addItem("JEUDI");
				 combojour.addItem("VENDREDI");
				 combojour.addItem("SAMEDI");
				 panel_1.add(combojour);
				 
				 JLabel lbheure = new JLabel("Heure");
				 lbheure.setFont(new Font("Arial", Font.BOLD, 16));
				 lbheure.setBounds(10, 98, 150, 25);
				 panel_1.add(lbheure);
				 
				 JComboBox comboheure = new JComboBox();
				 comboheure.addItem("");
				 comboheure.addItem("1ere H");
				 comboheure.addItem("2eme H");
				 comboheure.addItem("3eme H");
				 comboheure.addItem("4eme H");
				 comboheure.addItem("5eme H");
				 comboheure.addItem("6eme H");
				 comboheure.addItem("1ere et 2eme H");
				 comboheure.addItem("3eme  et 4eme H");
				 comboheure.addItem("5eme et 6eme H");
				 comboheure.addItem("2eme et 3eme H");
				 comboheure.addItem("4eme et 5eme H");
				 comboheure.setBounds(173, 100, 150, 25);
				 panel_1.add(comboheure);
				 
				 JLabel lbmatri_ens = new JLabel("Matricule enseignant");
				 lbmatri_ens.setFont(new Font("Arial", Font.BOLD, 16));
				 lbmatri_ens.setBounds(10, 133, 170, 25);
				 panel_1.add(lbmatri_ens);
				 
				 tfmatri_ens = new JTextField();
				 tfmatri_ens.setBounds(173, 133, 150, 25);
				 panel_1.add(tfmatri_ens);
				 
				 JLabel lblNewLabel = new JLabel("");
				 ImageIcon img_st= new ImageIcon(this.getClass().getResource("/st.jpg") ); 
				 lblNewLabel.setIcon(img_st);
				 lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				 lblNewLabel.setBounds(20, 308, 208, 279);
				 pn.add(lblNewLabel);
				 String rq2="select * from tb_cours order by id desc";
				 try{
					 st=con.laConnection().createStatement();
					 rst=st.executeQuery(rq2);
					 while(rst.next()){
						 df2.addRow(new Object[]{
		rst.getString("classe"),rst.getString("matiere"),rst.getString("jour"),rst.getString("heure"),
		rst.getString("matricule_ens")
								 });
						 
					   } 
					 }
					 
				 catch(SQLException ex){
				    	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);	
				    }
				 btenrg2=new JButton("ENR");
				 btenrg2.setHorizontalAlignment(SwingConstants.LEFT);
				 btenrg2.setBackground(new Color(0, 128, 128));
				 btenrg2.setForeground(new Color(0, 0, 0));
				 Image img_en2= new ImageIcon(this.getClass().getResource("/save.png") ).getImage(); 
				 btenrg2.setIcon(new ImageIcon(img_en2));
					btenrg2.setBounds(238,535,120,25);
					btenrg2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ev){
							String classe,matiere,jour,heure,matri_ens;
							classe=comboclasse.getSelectedItem().toString();
							matiere=tfmatiere.getText();
							jour=combojour.getSelectedItem().toString();
							heure=comboheure.getSelectedItem().toString();
							matri_ens=tfmatri_ens.getText();
				String rq1="insert into tb_cours(classe,matiere,jour,heure,matricule_ens) values('"+classe+"','"+matiere+"','"+jour+"','"+heure+"','"+matri_ens+"')";
							try{
								st=con.laConnection().createStatement();
								if(!matiere.equals("")&&!classe.equals("")&&!jour.equals("")&&!heure.equals("")){
								st.executeUpdate(rq1);
					    		JOptionPane.showMessageDialog(null,"Insertion reussie!",null,JOptionPane.INFORMATION_MESSAGE);
								}
								else{
									JOptionPane.showMessageDialog(null,"Completez le formulaire!",null,JOptionPane.ERROR_MESSAGE);
								}
							}
							catch(SQLException ex){
						    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
						    }
							String rq2="update tb_cours set num_jour=1 where jour='LUNDI'";
							String rq3="update tb_cours set num_jour=2 where jour='MARDI'";
							String rq4="update tb_cours set num_jour=3 where jour='MERCREDI'";
							String rq5="update tb_cours set num_jour=4 where jour='JEUDI'";
							String rq6="update tb_cours set num_jour=5 where jour='VENDREDI'";
							String rq7="update tb_cours set num_jour=6 where jour='SAMEDI'";
							try{
								st=con.laConnection().createStatement();
								st.executeUpdate(rq2);
								st.executeUpdate(rq3);
								st.executeUpdate(rq4);
								st.executeUpdate(rq5);
								st.executeUpdate(rq6);
								st.executeUpdate(rq7);
							}
							catch(SQLException ex){
						    	JOptionPane.showMessageDialog(null,"Erreur!",null,JOptionPane.ERROR_MESSAGE);	
						    }
							dispose();
							Cours crs=new Cours();
							crs.setVisible(true);
						}
						
					});
					pn.add(btenrg2);
		
	}
	private void init(){
		table=new JTable();
		scroll=new JScrollPane();
		scroll.setBounds(609,61,460,200);
		scroll.setViewportView(table);
		
	}
	private void init2(){
		table2=new JTable();
		scroll2=new JScrollPane();
		scroll2.setBounds(609,347,467,200);
		scroll2.setViewportView(table2);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cours cr=new Cours();
		cr.setVisible(true);
	

	}
}
