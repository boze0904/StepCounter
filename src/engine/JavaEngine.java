/**
 * �\�[�X�̃X�e�b�v���A�R�����g�s�A��s���J�E���g����G���W���ł��B
 *
 */
package engine;

import java.io.*;

public class JavaEngine implements Engineable{
/* ---------------------------------------------------------
 ���C��
--------------------------------------------------------- */
	public static void main(String[] args) throws Exception{
		File src = new File("t.java");
		Engineable engine = new JavaEngine(src);
		engine.parse();
		System.out.println("���s��:" + engine.getLinage());
		System.out.println("�X�e�b�v:" + engine.getStep());
		System.out.println("�R�����g:" + engine.getComment());
		System.out.println("��:" + engine.getEmpty());
	}
/* ---------------------------------------------------------
 �萔
--------------------------------------------------------- */
	/** �P��R�����g */
	public static final String c_single = "//";
	/** JavaDoc���R�����g�J�n */
	public static final String c_doc_s = "/*";
	/** JavaDoc���R�����g�I�� */
	public static final String c_doc_e = "*/";
	/** Java print���\�b�h */
	public static final String m_print = "System.out.print";
	/** Java printStackTrace���\�b�h */
	public static final String m_trace = "printStackTrace";

	/** �ύX�J�n */
	public static final String v_s = "@START STEP.5";
	/** �ύX�I�� */
	public static final String v_e = "@END STEP.5";

/* ---------------------------------------------------------
 �ϐ�
--------------------------------------------------------- */
	/** Java�\�[�X�t�@�C�� */
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
	/** Java print���\�b�h�� */
	private int print;
	/** Java �g���[�X���\�b�h�� */
	private int trace;

	/** �ύX�t���O */
	private boolean v_c_flag;
	/** �ύX�s�� */
	private int change;

/* ---------------------------------------------------------
 �R���X�g���N�^
--------------------------------------------------------- */
	/**
	 * ��͂���t�@�C�����w�肵�� JavaEngine �C���X�^���X�𐶐����܂��B
	 *
	 * @param src		Java�\�[�X�t�@�C��
	 */
	public JavaEngine(File src){
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

			// �ύX�������f
			if(line.indexOf(v_s) != -1){
				v_c_flag = true;
			}
			if(line.indexOf(v_e) != -1){
				v_c_flag = false;
			}

			// �R�����g�p�����f
			if(c_flag){
				comment++;
				if(line.indexOf(c_doc_e) != -1){
					c_flag = false;
				}
				continue;
			}
			// doc�R�����g�s
			if(line.indexOf(c_doc_s) == 0){
				c_flag = true;
				if(line.indexOf(c_doc_e) != -1){
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
			// Java print���\�b�h
			if(line.indexOf(m_print) != -1){
				print++;
			}
			// Java printStackTrace���\�b�h
			if(line.indexOf(m_trace) != -1){
				trace++;
			}
			// �X�e�b�v�s
			step++;

			// �ύX����
			if(v_c_flag){
				change++;
			}
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
	 * �f�o�b�O�p System.out.print���\�b�h�擾
	 *
	 * @return System.out.print���\�b�h
	 */
	public int getPrint(){
		return print;
	}
/* ---------------------------------------------------------
 printStackTrace���\�b�h�擾
--------------------------------------------------------- */
	/**
	 * �f�o�b�O�p printStackTrace���\�b�h�擾
	 *
	 * @return printStackTrace���\�b�h
	 */
	public int getTrace(){
		return trace;
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
		return change;
	}

}
