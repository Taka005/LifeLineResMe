package lifelineResMe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class main extends JFrame{

	private JPanel content;

	public static void main(String[] args){
		Clip clip = createClip();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					main frame = new main();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	public main(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(main.class.getResource("/assets/lifeline.ico")));
		setFont(new Font("Dialog",Font.PLAIN,60));
		setTitle("LifeLine Res Me");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,486,125);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5,5,5,5));

		setContentPane(content);
		
		JLabel Text = new JLabel("LifeLine Res Me!!");
		Text.setFont(new Font("MS UI Gothic",Font.PLAIN,60));
		Text.setVerticalAlignment(SwingConstants.BOTTOM);
		content.add(Text);
	}
	
	public static Clip createClip(){
		try(AudioInputStream ais = AudioSystem.getAudioInputStream(main.class.getResource("/assets/audio.wav"))){
			AudioFormat af = ais.getFormat();
			DataLine.Info dataLine = new DataLine.Info(Clip.class,af);
			Clip c = (Clip)AudioSystem.getLine(dataLine);
			c.open(ais);
			
			return c;
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(UnsupportedAudioFileException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(LineUnavailableException e){
			e.printStackTrace();
		}
		return null;
	}

}