/**
 * �\�[�X�̃X�e�b�v���A�R�����g�s�A��s���J�E���g����G���W���ł��B
 *
 */
package engine;

import java.io.*;

public class CEngine implements Engineable{
/* ---------------------------------------------------------
 ���C��
--------------------------------------------------------- */
	public static void main(String[] args) throws Exception{
		File src = new File("t.c");
		Engineable engine = new CEngine(src);
		engine.parse();
		System.out.println("���s��:" + engine.getLinage());
		System.out.println("�X�e�b�v:" + engine.getStep());
		System.out.println("�R�����g:" + engine.getComment());
		System.out.println("��:" + engine.getEmpty());
	}
/* ---------------------------------------------------------
 �萔
--------------------------------------------------------- */
	/** C++�R�����g */
	public static final String c_single = "//";
	/** C�R�����g�J�n */
	public static final String c_s = "/*";
	/** C�R�����g�I�� */
	public static final String c_e = "*/";

/* ---------------------------------------------------------
 �ϐ�
--------------------------------------------------------- */
	/** C�\�[�X�t�@�C�� */
	private File src;

	/** ���s�� */
	private int linage;
	/** �R�����g�t���O */
	private boolean c_flag;
	/** �X�e�b�v�� */
	private int step;
	/** �R�����g�� */
	private int comment;
	/** ��s�� */
	private int empty;
	/** printf���\�b�h�� */
	private int print;

	/** �ύX�t���O */
	private boolean v_c_flag;
	/** �ύX�s�� */
	private int change;

/* ---------------------------------------------------------
 �R���X�g���N�^
--------------------------------------------------------- */
	/**
	 * ��͂���t�@�C�����w�肵�� CEngine �C���X�^���X�𐶐����܂��B
	 *
	 * @param src		C�\�[�X�t�@�C��
	 */
	public CEngine(File src){
		this.src = src;
	}
/* ---------------------------------------------------------
 ���
--------------------------------------------------------- */
	/**
	 * ��͂��܂��B
	 *
	 * @throws Exception	��͒��ɃG���[�����������ꍇ
	 */
	public void parse() throws Exception{
		int single;
		BufferedReader br = new BufferedReader(new FileReader(src));
		String line = null;
		while((line = br.readLine()) != null){
			line = line.trim();
			// ���s��
			linage++;

			// �R�����g�p�����f
			if(c_flag){
				comment++;
				if(line.indexOf(c_e) != -1){
					c_flag = false;
				}
				continue;
			}
			// �R�����g�s
			if(line.indexOf(c_s) == 0){
				c_flag = true;
				if(line.indexOf(c_e) != -1){
					c_flag = false;
				}
				comment++;
				continue;
			}
			// �P��R�����g
			if((single = line.indexOf(c_single)) != -1){
				if(single == 0){
					comment++;
					continue;
				}
			}
			// ��s
			if(line == null || line.length() == 0){
				empty++;
				continue;
			}
			// �X�e�b�v�s
			step++;

		}
	}
/* ---------------------------------------------------------
 ���s��
--------------------------------------------------------- */
	/**
	 * ���s�����擾���܂��B
	 *
	 * @return ���s��
	 */
	public int getLinage(){
		return linage;
	}
/* ---------------------------------------------------------
 �X�e�b�v���擾
--------------------------------------------------------- */
	/**
	 * �X�e�b�v�����擾���܂��B
	 *
	 * @return �X�e�b�v��
	 */
	public int getStep(){
		return step;
	}
/* ---------------------------------------------------------
 �R�����g�s�擾
--------------------------------------------------------- */
	/**
	 * �R�����g�s�擾
	 *
	 * @return �R�����g�s
	 */
	public int getComment(){
		return comment;
	}
/* ---------------------------------------------------------
 ��s�擾
--------------------------------------------------------- */
	/**
	 * ��s�擾
	 *
	 * @return ��s
	 */
	public int getEmpty(){
		return empty;
	}
/* ---------------------------------------------------------
 print���\�b�h�擾
--------------------------------------------------------- */
	/**
	 * �f�o�b�O�p printf���\�b�h�擾
	 *
	 * @return printf���\�b�h��
	 */
	public int getPrint(){
		return 0;
//		return print;
	}
/* ---------------------------------------------------------
 �g���[�X���\�b�h�擾
--------------------------------------------------------- */
	/**
	 * �f�o�b�O�p �g���[�X���\�b�h�擾
	 *
	 * @return �g���[�X���\�b�h��
	 */
	public int getTrace(){
		return 0;
	}
/* ---------------------------------------------------------
 �ύX�擾
--------------------------------------------------------- */
	/**
	 * �ύX�擾
	 *
	 * @return �ύX�s
	 */
	public int getChange(){
		return 0;
	}

}
