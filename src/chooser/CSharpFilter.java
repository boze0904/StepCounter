/**
 * C#ファイル専用 FileFilter です。
 *
 *
 */
package chooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CSharpFilter extends FileFilter{
/* ---------------------------------------------------------
 定数
--------------------------------------------------------- */
	/** タイプ */
	public static final String type = "C#";
	/** 拡張子 */
	public static final String extension = ".cs";
	/** Description */
	public static final String discription = "C#ソースファイル(*.cs)";
/* ---------------------------------------------------------
 コンストラクタ
--------------------------------------------------------- */
	/**
	 * デフォルトコンストラクタです。
	 *
	 */
	public CSharpFilter(){
	}
/* ---------------------------------------------------------
 実装
--------------------------------------------------------- */
	/**
	 * このフィルタが指定されたファイルを受け付けるかどうかを返します。 
	 *
	 * @param f		受け入れるファイル
	 * @return 		true - 受け入れる false - 拒否
	 */
	public boolean accept(File f){
		if(f == null){
			return false;
		}
		if(f.isDirectory()){
			return true;
		}else{
			return f.getName().toLowerCase().endsWith(extension);
		}
	}
	/**
	 * このフィルタの説明です。
	 *
	 * @return		説明
	 */
	public String getDescription(){
		return discription;
	}
}
