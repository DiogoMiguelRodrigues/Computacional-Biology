package exercise;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Graphics {
	Simulator sim = new Simulator();
	final JFrame frame = new JFrame("Sequence evaluation simulator");
	
    public Graphics() {
    	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton input = new JButton("Input FASTA file");
        JButton inputc = new JButton("Input FASTA file with copies");
        JButton generate = new JButton("Generate sequence");
        JButton genNoriginal = new JButton("Generate N sequences");
        //JButton reset = new JButton("Reset parameter");
        JButton about = new JButton("About");
        JButton exit = new JButton("Exit");
        
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1));
        buttonPanel.add(input);
        buttonPanel.add(inputc);
        buttonPanel.add(generate);
        buttonPanel.add(genNoriginal);
        //buttonPanel.add(reset);
        buttonPanel.add(about);
        buttonPanel.add(exit);

        input.addActionListener(new ActionListener()
        {

		@Override
		public void actionPerformed(ActionEvent e) {
			sim.setOrgSeq(0);
			sim.input();
			getvalues();
			execute();
			
			sim.output();
			JOptionPane.showMessageDialog(frame, "The output was successful!\n"+"(1) output file have been generated!", "Successful", JOptionPane.INFORMATION_MESSAGE);
		}
        });
        
        inputc.addActionListener(new ActionListener()
        {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		sim.setOrgSeq(-2);
        		sim.input();
        		getvalues();
        		execute();
        		sim.output();
        		JOptionPane.showMessageDialog(frame, "The output was successful!\n"+"(1) output file have been generated!", "Successful", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        generate.addActionListener(new ActionListener()
        {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		getparameters();
        		sim.generate();
        		getvalues();
        		execute();
        		sim.output();
        		JOptionPane.showMessageDialog(frame, "The output was successful!\n"+"(1) output file have been generated!", "Successful", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        genNoriginal.addActionListener(new ActionListener()
        {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		getN();
        		getparameters();
        		getvalues();
        		sim.genNrandoms();
        		execute();
        		sim.output();
        		JOptionPane.showMessageDialog(frame, "The output was successful!\n"+"(1) output file have been generated!", "Successful", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
/*        
        reset.addActionListener(new ActionListener()
        {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		sim.setOrgSeq(0);
        	}
        });
*/        
        about.addActionListener(new ActionListener()
        {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(frame, "IST 1st Semester 2015/2016\n\nComputacional Biology\n\nGroup 9\nVanessa Gaspar\nDiogo Rodrigues\nNuno Pires", "About", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        exit.addActionListener(new ActionListener()
        {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        
        JPanel east = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        east.add(buttonPanel, gbc);

        JPanel center = new JPanel(){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Dimension getPreferredSize() {
                return new Dimension(200, 200);
            }
        };
        
        JLabel label1 = new JLabel("Welcome to simulator!");
        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));
   
        center.add(label1);
        
        frame.add(east, BorderLayout.EAST);
        frame.add(center);

        frame.pack();
        frame.setVisible(true);
    }

    public void getvalues(){
    	sim.setGEN(Integer.parseInt((String)JOptionPane.showInputDialog(frame,"Insert the number of generations:\n","Generations",JOptionPane.PLAIN_MESSAGE,null, null, null)));
		sim.setMR(Double.parseDouble((String)JOptionPane.showInputDialog(frame,"Insert the mutation rate:\n","Mutation Rate",JOptionPane.PLAIN_MESSAGE,null, null, "Insert values between 0 and 1")));
		sim.setRR(Double.parseDouble((String)JOptionPane.showInputDialog(frame,"Insert the recombination rate:\n","Recombination Rate",JOptionPane.PLAIN_MESSAGE,null, null, "Insert values between 0 and 1")));
		sim.setRFL(Integer.parseInt((String)JOptionPane.showInputDialog(frame,"Insert the recombination fragment length:\n","Recombination Fragment Length",JOptionPane.PLAIN_MESSAGE,null, null, null)));	
    }
    
    public void getparameters(){
    	sim.setPS(Integer.parseInt((String)JOptionPane.showInputDialog(frame, "Insert the population size:\n", "Population Size", JOptionPane.PLAIN_MESSAGE, null, null, null)));
    	sim.setSS(Integer.parseInt((String)JOptionPane.showInputDialog(frame, "Insert the sequence size:\n", "Sequence Size", JOptionPane.PLAIN_MESSAGE, null, null, null)));
    }
    
    public void execute(){
    	for(int i=0; i<sim.getGEN();i++){
    		sim.setActualGen(i+1);
			sim.mutation();
			sim.recombine();
			sim.hammingDistance();
			sim.jukesCantorModel();
			sim.plot();
		}
    }
    
    public void getN(){
    	sim.setOrgSeq(Integer.parseInt((String)JOptionPane.showInputDialog(frame, "Insert the number of originals sequences:\n", "Original Sequences", JOptionPane.PLAIN_MESSAGE, null, null, null)));
    	}
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Graphics();
            }
        });
    }
}   