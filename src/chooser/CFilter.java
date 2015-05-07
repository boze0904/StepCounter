/**
 * Cファイル専用 FileFilter です。
 *
 *
 */
package chooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CFilter extends FileFilter{
/* ---------------------------------------------------------
 定数
--------------------------------------------------------- */
	/** タイプ */
	public static final String type = "c";
	/** 拡張子 */
	public static final String extension_cpp = ".cpp";
	public static final String extension_h = ".h";
	public static final String extension_c = ".c";
	/** Description */
	public static final String discription = "Cソースファイル(*.cpp *.c *.h)";
/* ---------------------------------------------------------
 コンストラクタ
--------------------------------------------------------- */
	/**
	 * デフォルトコンストラクタです。
	 *
	 */
	public CFilter(){
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
			boolean cpp = f.getName().toLowerCase().endsWith(extension_cpp);
			boolean h = f.getName().toLowerCase().endsWith(extension_h);
			boolean c = f.getName().toLowerCase().endsWith(extension_c);
			if(cpp || h || c){
				return true;
			}else{
				return false;
			}
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
