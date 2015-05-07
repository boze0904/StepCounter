/**
 * �ȈՃe�[�u��.
 * �Z���𒼐ڕҏW�ł��Ȃ��B
 * SimpleTableModel���g�p���Ă���B
 * 
 * @version $Revision: 1.1 $ $Date: 2003/01/28 11:59:22 $
 */
package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 * �ȈՃe�[�u��.
 * �Z���𒼐ڕҏW�ł��Ȃ��B
 * SimpleTableModel���g�p���Ă���B
 * 
 * @version $Revision: 1.1 $ $Date: 2003/01/28 11:59:22 $
 * @author $Author: inagashima $
 */
public class SimpleTable extends JTable {
/* -------------------------------------------------------------------
 �t�B�[���h
------------------------------------------------------------------- */
	/** �e�[�u�����f�� */
	protected SimpleTableModel simpleModel = new SimpleTableModel();
/* -------------------------------------------------------------------
 �R���X�g���N�^
------------------------------------------------------------------- */
	/**
	 * �V���v���e�[�u�����쐬����
	 */
	public SimpleTable() {
		setModel(simpleModel);
	}

/* -------------------------------------------------------------------
 �����T�C�Y
------------------------------------------------------------------- */
	/** �����T�C�Y�� */
	private int PreferredViewportSizeWidth = -1;
	/**
	 * �����T�C�Y���ݒ�
	 *
	 * @param	width	�����T�C�Y��
	 */
	public void setPreferredViewportSizeWidth(int width) {
		PreferredViewportSizeWidth = width;
	}
	/**
	 * �����T�C�Y���擾
	 *
	 * @return	�����T�C�Y��
	 */
	public int getPreferredViewportSizeWidth() {
		return PreferredViewportSizeWidth;
	}
	/** �����T�C�Y����(�s��) */
	private int PreferredViewportSizeHeight = -1;
	/**
	 * �����T�C�Y����(�s��)�ݒ�
	 *
	 * @param	row	�����T�C�Y����(�s��)
	 */
	public void setPreferredViewportSizeHeight(int row) {
		PreferredViewportSizeHeight = row;
	}
	/**
	 * �����T�C�Y����(�s��)�擾
	 *
	 * @return	�����T�C�Y����(�s��)
	 */
	public int getPreferredViewportSizeHeight() {
		return PreferredViewportSizeHeight;
	}
	/**
	 * ���̃e�[�u���̃r���[�|�[�g�̖]�܂����T�C�Y��Ԃ��܂��B
	 * �����\���s����ݒ肵���ꍇ�A���̍s���̂ݕ\���\�ȃr���[�|�[�g�T�C�Y���v�Z���܂��B
	 *
	 * @return	�����\���s��
	 */
	public Dimension getPreferredScrollableViewportSize() {
		Dimension size = super.getPreferredScrollableViewportSize();
		if (PreferredViewportSizeWidth != -1) {
			size.width = PreferredViewportSizeWidth;
		}
		if (PreferredViewportSizeHeight != -1) {
			size.height = getRowHeight() * PreferredViewportSizeHeight;
		}
		return size;
	}
/* -------------------------------------------------------------------
 �J�������w��
------------------------------------------------------------------- */
	/**
	 * �J�������w��
	 *
	 * @param	columnName	�J������
	 */
	public void setColumnNames(String[] columnName) {
		simpleModel.setColumnNames(columnName);
	}
/* -------------------------------------------------------------------
 �f�[�^�ǉ�
------------------------------------------------------------------- */
	/**
	 * �f�[�^�ǉ�
	 *
	 * @param	data	�f�[�^�z��
	 */
	public void addRow(Object[] data) {
		simpleModel.addRow(data);
	}
/* -------------------------------------------------------------------
 �f�[�^�\���J�n�J�����̎w��
------------------------------------------------------------------- */
	/**
	 * �f�[�^�\���J�n�J�����w��
	 *
	 * @param	c	�f�[�^�\���J�n�J����
	 */
	public void setStartColumn(int c) {
		simpleModel.setStartColumn(c);
	}
/* -------------------------------------------------------------------
 �f�[�^�N���A
------------------------------------------------------------------- */
	/**
	 * �f�[�^�N���A.
	 * �S�Ă̍s�����N���A����B
	 */
	public void clear() {
		simpleModel.clear();
	}
/* -------------------------------------------------------------------
 �f�[�^�擾
------------------------------------------------------------------- */
	/**
	 * �f�[�^�擾.
	 * �f�[�^�\���J�n�J�����l�𖳎����Ē��ڃf�[�^���擾����B
	 *
	 * @param	row		�s
	 * @param	column	��
	 */
	public Object getData(int row, int column) {
		return simpleModel.getData(row, column);
	}
/* -------------------------------------------------------------------
 �J�����̐������w��
------------------------------------------------------------------- */
	/**
	 * �J�����̐������w��.
	 *
	 * @param	widths	�J�������z��
	 */
	public void setPreferredColumnWidth(int[] widths) {
		TableColumnModel tcm = getColumnModel();
		for(int i = 0; i < widths.length && i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setPreferredWidth(widths[i]);
		}
	}
/* -------------------------------------------------------------------
 �s�̎擾
------------------------------------------------------------------- */
	/**
	 * �s�̎擾
	 *
	 * @param	row		�s�ԍ�
	 * @return			�s�f�[�^
	 */
	public Object[] getRow(int row) {
		return simpleModel.getRow(row);
	}
/* -------------------------------------------------------------------
 �s�̍폜
------------------------------------------------------------------- */
	/**
	 * �s�̍폜
	 *
	 * @param row		�s�ԍ�
	 */
	public void removeRow(int row){
		simpleModel.removeRow(row);
	}
/* -------------------------------------------------------------------
 �s�̍폜
------------------------------------------------------------------- */
	/**
	 * �s�̍폜
	 *
	 * @param row		�s�ԍ�
	 */
	public void removeRow(int[] row){
		simpleModel.removeRow(row);
	}
}
