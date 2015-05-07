/**
 * �t�@�C���`���[�U�ł�
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
 ���C��
--------------------------------------------------------- */
	public static void main(String[] args){
		Chooser chooser = new Chooser();
		File[] list = chooser.getFileList();
		for(int i = 0; i < list.length; i++){
			System.out.println(list[i].getAbsolutePath());
		}
		System.out.println("�^�C�v=" + chooser.getFilterType());
		System.exit(0);
	}
/* ---------------------------------------------------------
 �萔
--------------------------------------------------------- */
	/** ���̓t�@�C������ ���� */
	public static final String IN = "in";
	/** �o�̓t�@�C������ ���� */
	public static final String OUT = "out";
/* ---------------------------------------------------------
 �ϐ�
--------------------------------------------------------- */
	/** JFileChooser */
	private JFileChooser fc;
	/** �e�R���|�[�l���g */
	private Component parent;
	/** �ŐV���̓f�B���N�g�� */
	private static File inCurrent;
	/** �ŐV�o�̓f�B���N�g�� */
	private static File outCurrent;
	/** �t�@�C�����i�[���� Vector�C���X�^���X */
	private Vector src = new Vector();
	/** �o�̓t�@�C�� */
	private File outFile;
	/** �I������t�@�C���^�C�v */
	private String type;
/* ---------------------------------------------------------
 �R���X�g���N�^
--------------------------------------------------------- */
	/**
	 * �f�t�H���g�œ��̓t�@�C�������t�@�C���`���[�U�𐶐����܂��B
	 *
	 */
	public Chooser(){
	}
	/**
	 * �t�@�C���`���[�U�𐶐����܂��B
	 *
	 * @param parent	�e�R���|�[�l���g
	 */
	public Chooser(Component parent){
		this.parent = parent;
	}
/* ---------------------------------------------------------
 ���̓t�@�C������JFileChooser ����
--------------------------------------------------------- */
	/**
	 * JFileChooser ���g���ăt�@�C���y�уf�B���N�g����I�����܂��B
	 *
	 * @return APPROVE_OPTION ���I�����ꂽ�ꍇ-true ����ȊO-false
	 */
	public boolean in(){
		fc = new JFileChooser();
		// Filter�ǉ�
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
 �o�̓t�@�C������JFileChooser ����
--------------------------------------------------------- */
	/**
	 * JFileChooser ���g���ăt�@�C���y�уf�B���N�g����I�����܂��B
	 *
	 * @return APPROVE_OPTION ���I�����ꂽ�ꍇ-true ����ȊO-false
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
 �o�̓t�@�C���擾(CSV)
--------------------------------------------------------- */
	/**
	 * �o�̓t�@�C�����擾���܂��B
	 *
	 * @return		�o�̓t�@�C��
	 */
	public File getOutFile(){
		return outFile;
	}
/* ---------------------------------------------------------
 �t�@�C�����o
--------------------------------------------------------- */
	/**
	 * �w�肵���t�@�C���̒�����f�B���N�g���������̓t�@�C����
	 * Filter ���环�ʂ��Ċi�[���܂��B<BR>
	 * �f�B���N�g���̏ꍇ�A���̒��̃t�@�C���y�уf�B���N�g���𒲂ׂ��
	 * �ċA�I�ɂ��̃��\�b�h���Ăяo���܂��B
	 *
	 * @param f		�t�@�C�����X�g
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
 Filter �^�C�v�擾
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
 �t�@�C�����X�g�擾
--------------------------------------------------------- */
	public File[] getFileList(){
		File[] f = new File[src.size()];
		for(int i = 0; i < src.size(); i++){
			f[i] = (File)src.get(i);
		}
		return f;
	}
}
