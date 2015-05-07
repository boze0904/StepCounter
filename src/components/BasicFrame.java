/**
 * JFrameを継承して、簡単なフレームを実装したコンポーネントです.
 *
 *
 * @version $Revision: 1.1.1.1 $ $Date: 2003/01/23 06:29:27 $
 */
package components;

import javax.swing.*;
import java.awt.*;

public class BasicFrame extends JFrame{
/* -------------------------------------------------------------------
 コンポーネント
------------------------------------------------------------------- */
	/** メインパネル */
	protected JPanel mainPanel = new JPanel();
/* -------------------------------------------------------------------
 コンストラクタ
------------------------------------------------------------------- */
	/**
	 * BasicFrame を生成します.
	 *
	 */
	public BasicFrame(){
		init();
	}
/* -------------------------------------------------------------------
 画面生成
------------------------------------------------------------------- */
	protected void init(){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		getContentPane().setLayout(gbl);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(8, 16, 8, 16);
		gbc.weightx = 1.0f;
		gbc.weighty = 1.0f;
		getContentPane().add(mainPanel, gbc);
	}
/* -------------------------------------------------------------------
 画面の中央寄せ
------------------------------------------------------------------- */
	/**
	 * 画面の中央寄せ
	 */
	public void centerScreen() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(d.getWidth() - this.getWidth())/2,
					(int)(d.getHeight() - this.getHeight())/2);
	}
/* -------------------------------------------------------------------
 メインパネルの取得
------------------------------------------------------------------- */
	/**
	 * メインパネルの取得.
	 *
	 * @return	メインパネル
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
