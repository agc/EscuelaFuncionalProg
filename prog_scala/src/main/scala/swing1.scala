package gui

import scala.swing._
import javax.swing.UIManager

object Main extends SimpleSwingApplication {
	
	def top= new MainFrame {

		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel)
		title="Test"
		val frameWidth=640
		val frameHeight=480
		val screenSize= java.awt.Toolkit.getDefaultToolkit().getScreenSize()
		location= new java.awt.Point((screenSize.width - frameWidth)/2,(screenSize.height-frameHeight)/2)


		minimumSize= new java.awt.Dimension(400,400)
	}
}