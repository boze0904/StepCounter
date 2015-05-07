/**
 * C#�t�@�C����p FileFilter �ł��B
 *
 *
 */
package chooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CSharpFilter extends FileFilter{
/* ---------------------------------------------------------
 �萔
--------------------------------------------------------- */
	/** �^�C�v */
	public static final String type = "C#";
	/** �g���q */
	public static final String extension = ".cs";
	/** Description */
	public static final String discription = "C#�\�[�X�t�@�C��(*.cs)";
/* ---------------------------------------------------------
 �R���X�g���N�^
--------------------------------------------------------- */
	/**
	 * �f�t�H���g�R���X�g���N�^�ł��B
	 *
	 */
	public CSharpFilter(){
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
