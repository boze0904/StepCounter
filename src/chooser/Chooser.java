/**
 * ファイルチューザです
 *
 */
package chooser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.util.*;
import java.io.*;

public class Chooser{
/* ---------------------------------------------------------
 メイン
--------------------------------------------------------- */
	public static void main(String[] args){
		Chooser chooser = new Chooser();
		File[] list = chooser.getFileList();
		for(int i = 0; i < list.length; i++){
			System.out.println(list[i].getAbsolutePath());
		}
		System.out.println("タイプ=" + chooser.getFilterType());
		System.exit(0);
	}
/* ---------------------------------------------------------
 定数
--------------------------------------------------------- */
	/** 入力ファイル処理 識別 */
	public static final String IN = "in";
	/** 出力ファイル処理 識別 */
	public static final String OUT = "out";
/* ---------------------------------------------------------
 変数
--------------------------------------------------------- */
	/** JFileChooser */
	private JFileChooser fc;
	/** 親コンポーネント */
	private Component parent;
	/** 最新入力ディレクトリ */
	private static File inCurrent;
	/** 最新出力ディレクトリ */
	private static File outCurrent;
	/** ファイルを格納する Vectorインスタンス */
	private Vector src = new Vector();
	/** 出力ファイル */
	private File outFile;
	/** 選択するファイルタイプ */
	private String type;
/* ---------------------------------------------------------
 コンストラクタ
--------------------------------------------------------- */
	/**
	 * デフォルトで入力ファイル処理ファイルチューザを生成します。
	 *
	 */
	public Chooser(){
	}
	/**
	 * ファイルチューザを生成します。
	 *
	 * @param parent	親コンポーネント
	 */
	public Chooser(Component parent){
		this.parent = parent;
	}
/* ---------------------------------------------------------
 入力ファイル処理JFileChooser 生成
--------------------------------------------------------- */
	/**
	 * JFileChooser を使ってファイル及びディレクトリを選択します。
	 *
	 * @return APPROVE_OPTION が選択された場合-true それ以外-false
	 */
	public boolean in(){
		fc = new JFileChooser();
		// Filter追加
		fc.addChoosableFileFilter(new JavaFilter());
		fc.addChoosableFileFilter(new CFilter());
		fc.addChoosableFileFilter(new CSharpFilter());

		fc.setFileFilter(fc.getAcceptAllFileFilter());
		fc.setCurrentDirectory(inCurrent);
		fc.setMultiSelectionEnabled(true);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			File[] file = fc.getSelectedFiles();
			type = getFilterType();
			setFile(file);
			inCurrent = fc.getCurrentDirectory();
			return true;
		}else{
			return false;
		}
	}
/* ---------------------------------------------------------
 出力ファイル処理JFileChooser 生成
--------------------------------------------------------- */
	/**
	 * JFileChooser を使ってファイル及びディレクトリを選択します。
	 *
	 * @return APPROVE_OPTION が選択された場合-true それ以外-false
	 */
	public boolean out(){
		fc = new JFileChooser();
		fc.addChoosableFileFilter(new CsvFilter());
		fc.setCurrentDirectory(outCurrent);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			String fileName = file.getPath();
			if (!fileName.toLowerCase().endsWith(CsvFilter.extension)){
				fileName = fileName + ".csv";
				file = new File(fileName);
			}
			outFile = file;
			type = getFilterType();
			outCurrent = fc.getCurrentDirectory();
			return true;
		}else{
			return false;
		}
	}
/* ---------------------------------------------------------
 出力ファイル取得(CSV)
--------------------------------------------------------- */
	/**
	 * 出力ファイルを取得します。
	 *
	 * @return		出力ファイル
	 */
	public File getOutFile(){
		return outFile;
	}
/* ---------------------------------------------------------
 ファイル抽出
--------------------------------------------------------- */
	/**
	 * 指定したファイルの中からディレクトリもしくはファイルを
	 * Filter から識別して格納します。<BR>
	 * ディレクトリの場合、その中のファイル及びディレクトリを調べる為
	 * 再帰的にこのメソッドを呼び出します。
	 *
	 * @param f		ファイルリスト
	 */
	private void setFile(File[] f){
		Vector dir = new Vector();
		for(int i = 0; i < f.length; i++){
			if(f[i].isDirectory()){
				dir.add(f[i]);
			}else{
				// Java
				if(type.equals(JavaFilter.type)){
					if(f[i].getName().toLowerCase().endsWith(JavaFilter.extension)){
						src.add(f[i]);
					}
				// C
				}else if(type.equals(CFilter.type)){
					boolean cpp = f[i].getName().toLowerCase().endsWith(CFilter.extension_cpp);
					boolean h = f[i].getName().toLowerCase().endsWith(CFilter.extension_h);
					boolean c = f[i].getName().toLowerCase().endsWith(CFilter.extension_c);
					if(cpp || h || c){
						src.add(f[i]);
					}
				// C#
				}else if(type.equals(CSharpFilter.type)){
					if(f[i].getName().toLowerCase().endsWith(CSharpFilter.extension)){
						src.add(f[i]);
					}
				}
			}
		}
		for(int i = 0; i < dir.size(); i++){
			setFile(((File)dir.get(i)).listFiles());
		}
	}
/* ---------------------------------------------------------
 Filter タイプ取得
--------------------------------------------------------- */
	public String getFilterType(){
		FileFilter filter = fc.getFileFilter();
		if(filter instanceof JavaFilter){
			return JavaFilter.type;
		}else if(filter instanceof CFilter){
			return CFilter.type;
		}else if(filter instanceof CSharpFilter){
			return CSharpFilter.type;
		}else{
			return "all";
		}
	}
/* ---------------------------------------------------------
 ファイルリスト取得
--------------------------------------------------------- */
	public File[] getFileList(){
		File[] f = new File[src.size()];
		for(int i = 0; i < src.size(); i++){
			f[i] = (File)src.get(i);
		}
		return f;
	}
}
