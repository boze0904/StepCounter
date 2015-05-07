/**
 * 解析メイン画面です。
 *
 */

import components.*;
import chooser.*;
import engine.*;
import util.*;


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class SrcCounter extends BasicFrame{
/* -------------------------------------------------------------------
 メイン
------------------------------------------------------------------- */
	public static void main(String[] args){
		new SrcCounter();
	}
/* -------------------------------------------------------------------
 コンポーネント
------------------------------------------------------------------- */
	/** メニューバー */
	private JMenuBar menubar = new JMenuBar();
	/** メニュー */
	private JMenu menu = new JMenu("ファイル");
	/** メニューアイテム 開く */
	private JMenuItem openItem = new JMenuItem("開く");
	/** メニューアイテム CSV出力 */
	private JMenuItem csvItem = new JMenuItem("CSV出力");
	/** メニューアイテム 終了 */
	private JMenuItem closeItem = new JMenuItem("終了");
	/** シンプルテーブル */
	protected SimpleTable table = new SimpleTable();
/* -------------------------------------------------------------------
 変数
------------------------------------------------------------------- */
	/** デフォルトのフォントを変更 */
	static { UI.setDefaultFont(); }
	/** カラム名 */
	protected String[] columnName = {"ソースファイル", "総行数", "ステップ行数", "コメント行数", "空行数",
										"Printメソッド", "トレースメソッド", "変更"};
	/** カラム名推奨幅 */
	protected int[] columnWidth = {300, 100, 100, 100, 100, 100, 100, 100};

/* -------------------------------------------------------------------
 コンストラクタ
------------------------------------------------------------------- */
	/**
	 * 解析メイン画面を構築します。
	 *
	 */
	public SrcCounter(){
		super();
		initComponents();
	}
/* -------------------------------------------------------------------
 コンポーネント初期化及び実装
------------------------------------------------------------------- */
	/**
	 * コンポーネント初期化及び実装をします。
	 *
	 */
	private void initComponents(){
		menubar.add(menu);
		menu.add(openItem);
		menu.add(csvItem);
		menu.addSeparator();
		menu.add(closeItem);
		setJMenuBar(menubar);

		JPanel panel = getMainPanel();
		panel.setLayout(new BorderLayout());

		table.setColumnNames(columnName);
		table.setPreferredColumnWidth(columnWidth);
		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(1050, 500));
		scroll.setViewportView(table);
		panel.add(scroll, BorderLayout.CENTER);

		setEvent();

		pack();
		centerScreen();
		setVisible(true);
	}
/* -------------------------------------------------------------------
 イベント設定
------------------------------------------------------------------- */
	/**
	 * イベントを設定します。
	 *
	 */
	private void setEvent(){
		// 開く
		openItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				processParse();
				setCursor(Cursor.getDefaultCursor());
			}
		});
		// CSV出力
		csvItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				processCsv();
				setCursor(Cursor.getDefaultCursor());
			}
		});
		// 終了
		closeItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		// ウインドゥクローズ
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
/* -------------------------------------------------------------------
 ファイル解析処理
------------------------------------------------------------------- */
	/**
	 * ファイル解析処理です。
	 *
	 */
	private void processParse(){
		Chooser chooser = new Chooser(this);
		boolean b = chooser.in();
		if(!b){
			return;
		}
		String type = chooser.getFilterType();
		File[] list = chooser.getFileList();
		table.clear();
		for(int i = 0; i < list.length; i++){
			Engineable engine = null;
			if(type.equals(JavaFilter.type)){
				engine = new JavaEngine(list[i]);
			}else if(type.equals(CFilter.type)){
				engine = new CEngine(list[i]);
			}else if(type.equals(CSharpFilter.type)){
				engine = new CSharpEngine(list[i]);
			}else{
				continue;
			}
			try{
				engine.parse();
				table.addRow(new Object[]{
					list[i].getAbsolutePath(),
					String.valueOf(engine.getLinage()),
					String.valueOf(engine.getStep()),
					String.valueOf(engine.getComment()),
					String.valueOf(engine.getEmpty()),
					String.valueOf(engine.getPrint()),
					String.valueOf(engine.getTrace()),
					String.valueOf(engine.getChange())
				});
			}catch(Exception e){
				table.addRow(new Object[]{
					list[i].getAbsolutePath(),
					"解析不能",
					"解析不能",
					"解析不能",
					"解析不能",
					"解析不能",
					"解析不能",
					"解析不能"
				});
			}
		}
	}
/* -------------------------------------------------------------------
 CSV出力処理
------------------------------------------------------------------- */
	/**
	 * CSV出力処理です。
	 *
	 */
	private void processCsv(){
		Chooser chooser = new Chooser(this);
		boolean b = chooser.out();
		if(!b){
			return;
		}
		File outFile = chooser.getOutFile();
		if(outFile == null){
			return;
		}
		if(outFile.exists()){
			int r = JOptionPane.showConfirmDialog(
						this,
						"上書きしてもよろしいですか",
						"確認",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
					);
			if(r == JOptionPane.NO_OPTION){
				return;
			}
		}
		PrintWriter out = null;
		try{
			out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outFile)), true);
		}catch(Exception e){
			e.printStackTrace();
			return;
		}

		StringBuffer column = new StringBuffer();
		for(int i = 0; i < columnName.length; i++){
			column.append(columnName[i]);
			if(i != (columnName.length - 1)){
				column.append(",");
			}
		}
		out.println(column.toString());

		SimpleTableModel model = (SimpleTableModel)table.getModel();
		for(int i = 0; i < model.getRowCount(); i++){
			StringBuffer sb = new StringBuffer();
			Object[] obj = table.getRow(i);
			int endLen = obj.length;
			for(int j = 0; j < obj.length; j++){
				String s = (String)obj[j];
				sb.append(s);
				if(j != (endLen - 1)){
					sb.append(",");
				}
			}
			out.println(sb.toString());
		}
		out.close();
	}
}
