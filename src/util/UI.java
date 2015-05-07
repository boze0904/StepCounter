/**
 * 画面の設定.<BR>
 * 画面の設定を共通して行う。
 * 
 * @version $Revision: 1.1.1.1 $ $Date: 2003/01/23 06:29:27 $
 */
package util;

import java.awt.*;
import java.util.*;
import java.awt.font.*;
import javax.swing.*;
import javax.swing.plaf.*;

public class UI {

	/**
	 * デフォルトフォントを指定する。
	 */
	public static void setDefaultFont() {
		UIDefaults	uid=UIManager.getDefaults();
		for(Enumeration e=uid.keys();e.hasMoreElements();)
		{
			try
			{
				String	key=(String)(e.nextElement());
				if(key.endsWith(".font"))
				{
					Font	font=UIManager.getFont(key);
					Map		attributes=font.getAttributes();
					if(attributes.get(TextAttribute.WEIGHT)!=null) {
						attributes.put(TextAttribute.WEIGHT,TextAttribute.WEIGHT_REGULAR);
//					} else if(attributes.get(TextAttribute.FAMILY)!=null) {
//						attributes.put(TextAttribute.FAMILY,"Monospaced");
					}
					UIManager.put(key,new FontUIResource(new Font(attributes)));
				} else if(key.equals("Label.foreground")) {
					UIManager.put(key,new ColorUIResource(Color.black));
				}
			}
			catch(Exception ne)
			{
			}
		}
	}
}
