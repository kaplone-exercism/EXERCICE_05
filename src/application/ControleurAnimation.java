package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControleurAnimation {
	
	public static Runnable launchGetRunnable(ImageView launch){
		
		Runnable r_launch = new Runnable() {		
			@Override
			public void run() {
				
				while (true){
					try {
						launch.setImage(new Image("launch_2.png"));	
						Thread.sleep(100);
						launch.setImage(new Image("launch.png"));	
						Thread.sleep(50);
						
					} catch (InterruptedException e) {
						launch.setImage(new Image("launch_nb.png"));
						e.printStackTrace();
					}
				}
				
			}
		};
		
		return r_launch;
	}
	
	public static Runnable mazeGetRunnable(ImageView maze){
		Runnable m_launch = new Runnable() {		
			@Override
			public void run() {
				
				while (true){
					try {
						maze.setImage(new Image("maze_v2_color1.png"));	
						Thread.sleep(80);
						maze.setImage(new Image("maze_v2_color2.png"));	
						Thread.sleep(50);
						maze.setImage(new Image("maze_v2_color3.png"));	
						Thread.sleep(70);
						
					} catch (InterruptedException e) {
						maze.setImage(new Image("maze_v2.png"));
						e.printStackTrace();
					}
				}
				
			}
		};
		
		return m_launch;
	}
	
	
	public static Runnable settingsGetRunnable(ImageView settings){
		Runnable s_launch = new Runnable() {		
			@Override
			public void run() {
				
				while (true){
					try {
						settings.setImage(new Image("settings2_r2.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r3.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r4.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r5.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r6.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r7.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r8.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r9.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r10.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r11.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r12.png"));	
						Thread.sleep(80);
						settings.setImage(new Image("settings2_r13.png"));	
						Thread.sleep(80);
						
					} catch (InterruptedException e) {
						settings.setImage(new Image("settings2_nb.png"));
						e.printStackTrace();
					}
				}
				
			}
		};
		return s_launch;
	}
	
	
	public static Runnable exitGetRunnable(ImageView exit){
		Runnable e_launch = new Runnable() {		
			@Override
			public void run() {
				
				while (true){
					try {
						exit.setImage(new Image("exit_0_01.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_01_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_02.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_02_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_03.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_03_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_04.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_04_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_05.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_05_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_06.png"));	
						Thread.sleep(300);
						exit.setImage(new Image("exit_0_05_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_05.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_04_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_04.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_03_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_03.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_02_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_02.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_01_.png"));	
						Thread.sleep(80);
						exit.setImage(new Image("exit_0_01.png"));	
						Thread.sleep(220);
						
					} catch (InterruptedException e) {
						exit.setImage(new Image("exit.png"));
						e.printStackTrace();
					}
				}		
			}
		};
		
		return e_launch;
	}

}
