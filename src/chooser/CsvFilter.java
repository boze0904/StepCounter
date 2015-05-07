/**
 * Csv�t�@�C����p FileFilter �ł��B
 *
 *
 */
package chooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CsvFilter extends FileFilter{
/* ---------------------------------------------------------
 �萔
--------------------------------------------------------- */
	/** �^�C�v */
	public static final String type = "csv";
	/** �g���q */
	public static final String extension = ".csv";
	/** Description */
	public static final String discription = "CSV�t�@�C��(*.csv)";
/* ---------------------------------------------------------
 �R���X�g���N�^
--------------------------------------------------------- */
	/**
	 * �f�t�H���g�R���X�g���N�^�ł��B
	 *
	 */
	public CsvFilter(){
	}
/* ---------------------------------------------------------
 ����
--------------------------------------------------------- */
	/**
	 * ���̃t�B���^���w�肳�ꂽ�t�@�C�����󂯕t���邩�ǂ�����Ԃ��܂��B 
	 *
	 * @param f		�󂯓����t�@�C��
	 * @return 		true - �󂯓���� false - ����
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
	 * ���̃t�B���^�̐����ł��B
	 *
	 * @return		����
	 */
	public String getDescription(){
		return discription;
	}
}