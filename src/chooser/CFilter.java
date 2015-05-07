/**
 * C�t�@�C����p FileFilter �ł��B
 *
 *
 */
package chooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CFilter extends FileFilter{
/* ---------------------------------------------------------
 �萔
--------------------------------------------------------- */
	/** �^�C�v */
	public static final String type = "c";
	/** �g���q */
	public static final String extension_cpp = ".cpp";
	public static final String extension_h = ".h";
	public static final String extension_c = ".c";
	/** Description */
	public static final String discription = "C�\�[�X�t�@�C��(*.cpp *.c *.h)";
/* ---------------------------------------------------------
 �R���X�g���N�^
--------------------------------------------------------- */
	/**
	 * �f�t�H���g�R���X�g���N�^�ł��B
	 *
	 */
	public CFilter(){
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
	 * ���̃t�B���^�̐����ł��B
	 *
	 * @return		����
	 */
	public String getDescription(){
		return discription;
	}
}
